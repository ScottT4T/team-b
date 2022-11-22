import React, { useEffect, useState } from "react";
import handSetType from "./HandsetCard.type";
import HandsetsCard from "../../components/HandsetCard";

const HandsetsPage = () => {
  const [handset, sethandset] = useState([] as handSetType[]);

  const url = "http://localhost:80/api/handsets";

  const fetchHandsets = async () => {
    fetch(url)
      .then((response) => response.json())
      .then((data) => {
        sethandset(data);
      });
  };
  useEffect(() => {
    fetchHandsets();
  }, []);

  return (
    <div>
      <HandsetsCard handsets={handset} />
    </div>
  );
};

export default React.memo(HandsetsPage);
