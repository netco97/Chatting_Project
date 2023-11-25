function getRoomInformations(){
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/information', true);
    xhr.send();
    xhr.onload = () => {
        if (xhr.status == 200) {
            console.log("success ! !");
            var obj = JSON.parse(xhr.response);
            console.log(obj[0]);
        } else {
            console.log("fail ! !");
        }
    }
}