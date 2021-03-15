const requestURL = 'https://cosmosdbsendall.azurewebsites.net/api/getHTTP?'
const tabledata = document.getElementById('tabledata')

// Fetch json data
async function fetchData (url) 
{
  return await fetch(url)
    .then(response => {
      return response.json()
    })
    .then(data => {
      return data
    })
    .catch(error => {
      console.log(error)
    })
}

async function getData () 
{
  let tempData = []
  let epoch = []
  let label
  await fetchData(requestURL) //Fetching JSON file
    .then(response => 
    {
      for (let row of response) 
      {
        console.log(row)
        tabledata.innerHTML += `<tr>
          <td>${row.deviceType}</td>
          <td>${row.DeviceId}</td>
            <td>${row.EpochT}</td>
            <td>${row.Temp}</td>
            <td>${row.Hum}</td>
            </tr>`
        tempData.push(row.Temp)
        epoch.push(row.EpochT)
        label = row.DeviceId

      }
      createChart(label, tempData, epoch)
    })
}
getData()

//Create chart.js
createChart = (label, tempData, epoch) => 
{
  var ctx = document.getElementById('myChart').getContext('2d')
  new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',

    // The data for our dataset
    data: {
      labels: epoch,
      datasets: [
        {
          label: label,
          backgroundColor: 'rgb(255, 0, 255)',
          borderColor: 'rgb(255, 99, 132)',
          data: tempData
        }
      ]
    },

    // Configuration options go here
    options: {}
  })
}