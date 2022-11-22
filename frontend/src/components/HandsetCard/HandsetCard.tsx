import "./HandsetsCard.css";

const HandsetsCard = ({ handsets, loading, hasError }: any) => {
  console.log(handsets);

  return (
    <>
      {hasError && <h1>Error</h1>}
      {loading ? (
        <h1>Loading</h1>
      ) : (
        handsets.map((handset: any) => (
          <>
            <div className="card">
              <div className="card-content">
                <img
                  className="imageCard"
                  src={handset.imageURL}
                  alt={handsets.displayName}
                />
              </div>
              <div className="card-content">
                <p className="secondaryText">{handset.brand}</p>
                <h4 className="titleText">{handset.displayName}</h4>
                <h4 className="titleText">{handset.memoryInternal}</h4>
                <p className="secondaryText">{handset.displayDescription}</p>
              </div>
            </div>
          </>
        ))
      )}
    </>
  );
};

export default HandsetsCard;
