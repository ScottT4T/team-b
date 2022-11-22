import React, { useState } from "react";
import logo from "./logo.svg";
import "./App.css";
import requestJSON from "./typescript/RequestJson/RequestJson";
import { HTTPMethods } from "./typescript/RequestJson/Constants";
import HandsetsPage from './pages/HandsetsPage';

//
//
// Response {type: 'basic', url: 'http://localhost/api/hello', redirected: false, status: 200, ok: true, …}
//
function App() {
  const [data, setdata] = useState<any>();

  return (
    <div className="App">
      <HandsetsPage />
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <button
          onClick={() => {
            window.location.href="http://localhost:3333/hello"
          }}
          >
          Test Server (http://localhost:3333/hello)
        </button>
        <br/>
        <button
          onClick={() => {
            window.location.href="http://localhost/api/hello"
          }}
        >
          Test API (http://localhost/api/hello)
        </button>
        <br/>
        <button
          onClick={async () => {
            const res = await requestJSON('/api/hello', HTTPMethods.GET)
              if (res) {
                setdata(JSON.stringify(res))
              }
          }}
        >
          Test UI (fetch /api/hello)
        </button>
        <div id="response">{data}</div>
      </header> */}
    </div>
  );
}

export default App;
