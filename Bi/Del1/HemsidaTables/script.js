let tabledata = document.getElementById('tabledata')

fetch("https://cosmosdbsendall.azurewebsites.net/api/GetFromTable")
.then(res => res.json())
.then(data => {
    console.log(data)
    for(let row of data){
        tabledata.innerHTML += `<tr><td>${row.rowKey}</td><td>${row.deviceId}</td><td>${row.timestampThing}</td><td>${row.lightSens}</td>`
    }
})