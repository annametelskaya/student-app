function createCard(data) {
    let cardBody;
    let card = "<div>";
    for (let i = 0; i < data.length; i++) {
        cardBody = "<div class='card m-1' id='" + data[i].groupNumber + "'style='width: 18em; display: inline-block'>" +
            "<div class='card-body'>" +
            "<img src='/resource/img/trash.png' id='delete' class='float-right action-item' onclick='deleteCard(" + data[i].groupNumber + ")' />" +
            "<h5 class='card-title'>Group " + data[i].groupNumber + "</h5>" +
            "</div>" +
            "</div>";
        card += cardBody;
    }
    card += "</div>";
    return card;
}

function searchCards() {
    clearTable();
    let group = document.getElementById('sortByGroup').value;
    document.getElementById('sortByGroup').value = group;
    let xhr = new XMLHttpRequest();
    let params = 'sortByGroup=' + group;
    xhr.open('GET', '/res/groups' + '?' + params, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let card = document.getElementById('groupsTable');
            card.insertAdjacentHTML('beforeend', createCard(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };
}

function logout() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/logout', true);
    xhr.send(null);
    window.location.replace('/loginPage');
}

function clearTable() {
    let node = document.getElementById('groupsTable');
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
}

function addGroup() {
    let input = {};
    input.groupNumber = document.getElementById("groupNumberForm").value;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/res/groups', true);
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

function deleteCard(id) {
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", '/res/groups?id=' + id, true);
    xhr.send();
    searchCards();
}