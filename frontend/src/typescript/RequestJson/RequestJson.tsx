import { HTTPCodes, HTTPMethods } from "./Constants"

const requestJSON = async (requestPath: any, method: HTTPMethods): Promise<any> => {
  return await fetch(requestPath, {
    method,
    headers: {
      "content-type": "application/json"
    }
  })
    .then( async (res?: Response) => {
      if (res?.status === HTTPCodes.OK) {
        return await res?.json().then((response) => {
          return response
        })
      } else {
        console.error('Bad request, please try again.')
      }
    })
    .catch(console.error)
  
}

export default requestJSON