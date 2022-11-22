import React, { useEffect, useState } from "react";
import handSetType from "./HandsetCard.type";

const HandsetsCard = ({ handsets, loading, hasError }: any) => {
  console.log(handsets);

  return (
    <>
      {/* <h1>{handsets}</h1> */}
      {hasError && <h1>Error</h1>}
      {loading ? (
        <h1>Loading</h1>
      ) : (
        handsets.map((handset: any) => (
          // console.log('>', handset)
          <div>{handset.id}</div>
        ))
      )}
    </>
  );
};

export default HandsetsCard;
