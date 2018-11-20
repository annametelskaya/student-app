function createTable(data) {
    let items;
    let row = "<div>";
    for (let i =0; i < data.length ; i++) {
        items = "<div class='row'>";
        items += "<div class='col-5 table-item'>" + data[i].firstName + "</div>";
        items += "<div class='col-6 table-item'>" + data[i].secondName + "</div>";
        items += "<div class='col-1 table-item'><img src='/resource/img/trash.png' onclick='deleteCard(" + data[i].id +")'></div>";
        items += "</div>";
        row += items;
    }
    row += "</div>";
    return row;
}


function deleteCard(id) {
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", '/res/professors?id=' + id, true);
    xhr.send();
    updateTable();
}

function updateTable() {
    clearTable();
    let name = document.getElementById('sortByName').value;
    document.getElementById('sortByName').value = name;
    let surname = document.getElementById('sortBySurname').value;
    document.getElementById('sortBySurname').value = surname;
    let xhr = new XMLHttpRequest();
    let params = 'sortByName=' + name +
        '&sortBySurname=' + surname;
    xhr.open('GET', '/res/professors' + '?' + params, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let card = document.getElementById('professorsTable');
            card.insertAdjacentHTML('beforeend', createTable(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}

function clearTable() {
    let node = document.getElementById('professorsTable');
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
}

function addProfessor() {
    let input = {};
    input.nameForm = document.getElementById("nameForm").value;
    input.surnameForm=document.getElementById("surnameForm").value;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/res/professors', true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    // xhr.onreadystatechange = function () {
    //     if (xhr.readyState === 4 && xhr.status === 200) {
    //         let json = JSON.parse(xhr.responseText);
    //         console.log(json.groupNumber);
    //     }
    // };
    let data = JSON.stringify(input);
    xhr.send(data);
}
