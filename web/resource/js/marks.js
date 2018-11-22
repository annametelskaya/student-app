function createTable(data) {
    let items;
    let row = "<div>";
    for (let i = 0; i < data.length; i++) {
        items = "<div class='row'>";
        items += "<div class='col-2 table-item'>" + data[i].subjectName + "</div>";
        items += "<div class='col-2 table-item'>" + data[i].studentName + "</div>";
        items += "<div class='col-2 table-item'>" + data[i].professorName + "</div>";
        items += "<div class='col-1 table-item'>" + data[i].mark + "</div>";
        items += "<div class='col-2 table-item'>" + data[i].date + "</div>";
        items += "<div class='col-2 table-item'>" + data[i].comment + "</div>";
        items += "<div class='col-1 table-item'><img src='/resource/img/trash.png' onclick='deleteCard(" + data[i].id + ")'></div>";
        items += "</div>";
        row += items;
    }
    row += "</div>";
    return row;
}

function logout() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/logout', true);
    xhr.send(null);
    window.location.replace('/loginPage');
}

function deleteCard(id) {
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", '/res/marks?id=' + id, true);
    xhr.send();
    updateTable();
}

function updateTable() {
    clearTable();
    let subject = document.getElementById('sortBySubject').value;
    document.getElementById('sortBySubject').value = subject;
    let student = document.getElementById('sortByStudent').value;
    document.getElementById('sortByStudent').value = student;
    let professor = document.getElementById('sortByProfessor').value;
    document.getElementById('sortByProfessor').value = professor;
    let mark = document.getElementById('sortByMark').value;
    document.getElementById('sortByMark').value = mark;
    let xhr = new XMLHttpRequest();
    let params = 'sortBySubject=' + subject +
        '&sortByStudent=' + student +
        '&sortByProfessor=' + professor +
        '&sortByMark=' + mark;
    xhr.open('GET', '/res/marks' + '?' + params, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let card = document.getElementById('markTable');
            card.insertAdjacentHTML('beforeend', createTable(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}

function clearTable() {
    let node = document.getElementById('markTable');
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
}

function addMarks() {
    let split = document.getElementById("selectedSubjectForm").value.split(",");
    let input = {};
    input.subject = {
        id: split[0]
    };
    input.student = {
        id: document.getElementById("selectStudentForm").value
    };
    input.professor = {
        id: split[1]
    };
    input.date = document.getElementById("date").value;
    input.mark = document.getElementById("markForm").value;
    input.comment = document.getElementById("commentForm").value;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/res/marks', true);
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


function getOptions() {
    getSubjects();
    getStudents();
}

function createSubjectsOptions(data) {
    let option;
    let options = "";
    for (let i = 0; i < data.length; i++) {
        option = "<option value=" + data[i].id + "," + data[i].professor.id + ">" + data[i].name + " - " + data[i].professor.firstName + " " + data[i].professor.secondName + "</option>";
        options += option;
    }
    return options;
}

function getSubjects() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/res/subjects', true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let select = document.getElementById('selectedSubjectForm');
            select.insertAdjacentHTML('beforeend', createSubjectsOptions(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}


function createStudentsOptions(data) {
    let option;
    let options = "";
    for (let i = 0; i < data.length; i++) {
        option = "<option value=" + data[i].id + ">" + data[i].firstName + " " + data[i].secondName + "</option>";
        options += option;
    }
    return options;
}

function getStudents() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/res/students', true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let select = document.getElementById('selectStudentForm');
            select.insertAdjacentHTML('beforeend', createStudentsOptions(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}
