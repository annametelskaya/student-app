function createTable(data) {
    let items;
    let row = "<div>";
    for (let i = 0; i < data.length; i++) {
        items = "<div class='row'>";
        items += "<div class='col-4 table-item'>" + data[i].firstName + "</div>";
        items += "<div class='col-4 table-item'>" + data[i].secondName + "</div>";
        items += "<div class='col-4 table-item'>" + data[i].groupNumber + "</div>";
        items += "</div>";
        row += items;
    }
    row += "</div>";
    return row;
}

function updateTable() {
    clearTable();
    let name = document.getElementById('sortByName').value;
    document.getElementById('sortByName').value = name;
    let surname = document.getElementById('sortBySurname').value;
    document.getElementById('sortBySurname').value = surname;
    let group = document.getElementById('sortByGroup').value;
    document.getElementById('sortByGroup').value = group;
    let xhr = new XMLHttpRequest();
    let params = 'sortByName=' + name +
        '&sortBySurname=' + surname +
        '&sortByGroup=' + group;
    xhr.open('GET', '/students' + '?' + params, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let card = document.getElementById('studentsTable');
            card.insertAdjacentHTML('beforeend', createTable(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}

function clearTable() {
    let node = document.getElementById('studentsTable');
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
}

function addStudent() {
    let input = {};
    input.firstName = document.getElementById("nameForm").value;
    input.secondName = document.getElementById("surnameForm").value;
    input.groupNumber = document.getElementById("groupForm").value;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/students', true);
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

function getGroups() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/groups', true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let card = document.getElementById('groupForm');
            card.insertAdjacentHTML('beforeend', createOptions(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}

function createOptions(data) {
    let option;
    let options = "";
    for (let i = 0; i < data.length; i++) {
        option = "<option value=" + data[i].groupNumber + ">" + data[i].groupNumber + "</option>";
        options += option;
    }
    return options;
}
