import React, { useEffect, useState } from "react";
import handSetType from "./HandsetCard.type";
import HandsetsCard from "../../components/HandsetCard";

const HandsetsPage = () => {
  const [handset, sethandset] = useState([] as handSetType[]);
  const [loading, setLoading] = useState(true);
  const [hasError, setErrors] = useState(null);

  const url = "http://localhost:80/api/handsets";

  const fetchHandsets = async () => {
    await fetch(url)
      .then((res) => res.json())
      .then((res) => {
        console.log(res);
        sethandset(res);
        setLoading(false);
      })
      .catch((err) => setErrors(err));
  };
  useEffect(() => {
    fetchHandsets();
  }, []);

  return (
    <div>
      {loading && !hasError && (
        <HandsetsCard
          handsets={handset}
          loading={loading}
          hasError={hasError}
        />
      )}
    </div>
  );
};

export default HandsetsPage;
