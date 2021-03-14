let tabledata = document.getElementById('tabledata')

fetch("https://cosmosdbsendall.azurewebsites.net/api/getHTTP?")
.then(res => res.json())
.then(data => {
    console.log(data)
    for(let row of data){
        tabledata.innerHTML += `<tr><td>${row.deviceType}</td><td>${row.DeviceId}</td><td>${row.EpochT}</td><td>${row.Temp}</td><td>${row.Hum}</td>`
    }
})