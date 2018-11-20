function createCard(data) {
    let cardBody;
    let card = "<div>";
    for (let i = 0; i < data.length; i++) {
        cardBody = "<div class='card m-1' style='width: 18em; display: inline-block'>" +
            "<div class='card-body'>" +
            "<img src='/resource/img/trash.png' id='delete' class='float-right action-item' onclick='deleteCard(" + data[i].id + ")' />" +
            "<h5 class='card-title'>Subject " + data[i].name + "</h5>" +
            "<p class='card-text'>Professor: " + data[i].professor.firstName + " " + data[i].professor.secondName + "<br> Hours:" + data[i].hours + "</p>" +
            "</div>" +
            "</div>";
        card += cardBody;
    }
    card += "</div>";
    return card;
}


function deleteCard(id) {
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", '/res/subjects?id=' + id, true);
    xhr.send();
    updateCards();
}

function updateCards() {
    clearCards();
    let name = document.getElementById('sortByName').value;
    document.getElementById('sortByName').value = name;
    let hours = document.getElementById('sortByHours').value;
    document.getElementById('sortByHours').value = hours;
    let professor = document.getElementById('sortByProf').value;
    document.getElementById('sortByProf').value = professor;
    let xhr = new XMLHttpRequest();
    let params = 'sortByName=' + name +
        '&sortByHours=' + hours +
        '&sortByProf=' + professor;
    xhr.open('GET', '/res/subjects' + '?' + params, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let card = document.getElementById('subjectsTable');
            card.insertAdjacentHTML('beforeend', createCard(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}

function clearCards() {
    let node = document.getElementById('subjectsTable');
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
}

function addSubject() {
    let input = {};
    input.selectedProfessor = document.getElementById("selectProfessorForm").value;
    input.hours = document.getElementById("hoursForm").value;
    input.subjectName = document.getElementById("subjectForm").value;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/res/subjects', true);
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

function createOptions(data) {
    let option;
    let options = "";
    for (let i = 0; i < data.length; i++) {
        option = "<option value=" + data[i].id + ">" + data[i].firstName + " " + data[i].secondName + "</option>";
        options += option;
    }
    return options;
}

function getProfessors() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/res/professors', true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let select = document.getElementById('selectProfessorForm');
            select.insertAdjacentHTML('beforeend', createOptions(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}