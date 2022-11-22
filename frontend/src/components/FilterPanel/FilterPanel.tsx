import React, { useEffect, useState } from "react";
import TreeView from "@material-ui/lab/TreeView";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import ChevronRightIcon from "@material-ui/icons/ChevronRight";
import TreeItem from "@material-ui/lab/TreeItem";
import { Checkbox, FormControlLabel } from "@material-ui/core";
import { RenderTree, data } from "./sampleData";

const FilterPanel = () => {
  const [selected, setSelected] = React.useState<string[]>([]);
  const url = "http://localhost:80/api/filter";
  const [filterData, setFilterData] = useState([]);

  useEffect(() => {
    fetch(url)
      .then((res) => res.json())
      .then((resData) => {
        const treeData: any = dataTreeMapper(resData);
        console.log("sampleData", data);
        console.log("tree", treeData);
        setFilterData(treeData);
      });
  }, []);

  function getChildById(node: RenderTree, id: string) {
    let array: string[] = [];

    function getAllChild(nodes: RenderTree | null) {
      if (nodes === null) return [];
      array.push(nodes.id);
      if (Array.isArray(nodes.children)) {
        nodes.children.forEach((node) => {
          array = [...array, ...getAllChild(node)];
          array = array.filter((v, i) => array.indexOf(v) === i);
        });
      }
      return array;
    }

    function getNodeById(nodes: RenderTree, id: string) {
      if (nodes.id === id) {
        return nodes;
      } else if (Array.isArray(nodes.children)) {
        let result = null;
        nodes.children.forEach((node) => {
          if (!!getNodeById(node, id)) {
            result = getNodeById(node, id);
          }
        });
        return result;
      }

      return null;
    }

    return getAllChild(getNodeById(node, id));
  }

  function getOnChange(checked: boolean, nodes: RenderTree) {
    console.log("selected", checked, nodes);
    const allNode: string[] = getChildById(data, nodes.id);
    let array = checked
      ? [...selected, ...allNode]
      : selected.filter((value) => !allNode.includes(value));

    array = array.filter((v, i) => array.indexOf(v) === i);
    console.log("selected", array);
    setSelected(array);
  }

  const renderTree = (nodes: any) =>
    nodes.id ? (
      <TreeItem
        key={nodes.id}
        nodeId={nodes.id}
        label={
          <FormControlLabel
            control={
              <Checkbox
                checked={selected.some((item) => {
                  console.log("item", item);
                  return item === nodes.id;
                })}
                onChange={(event) => {
                  console.log(nodes);
                  getOnChange(event.currentTarget.checked, nodes);
                }}
                onClick={(e) => e.stopPropagation()}
              />
            }
            label={<>{nodes.name}</>}
            key={nodes.id}
          />
        }
      >
        {Array.isArray(nodes.children)
          ? nodes.children.map((node: any) => renderTree(node))
          : null}
      </TreeItem>
    ) : null;

  const dataTreeMapper = (data: any) => {
    let childrenData = [];
    let finalData = {};
    childrenData = Object.keys(data).map((d, i) => {
      let mappedData: any = {} as any;
      mappedData["id"] = `${d}_${i}`;
      mappedData["name"] = d;
      mappedData["children"] = data[d].map((s: string, si: number) => {
        return {
          id: `${s}_${si}`,
          name: s,
        };
      });
      return mappedData;
    });
    finalData = {
      id: "0",
      name: "Root",
      children: childrenData,
    };
    return finalData;
  };

  return (
    <TreeView
      defaultCollapseIcon={<ExpandMoreIcon />}
      defaultExpanded={["0", "3", "4"]}
      defaultExpandIcon={<ChevronRightIcon />}
    >
      <>{renderTree(filterData)}</>
    </TreeView>
  );
};

export default React.memo(FilterPanel);
