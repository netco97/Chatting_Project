function createTopic(topic, period){
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('topic', topic);
    formData.append('period', period);
    xhr.open('POST', '/createTopic', true);
    xhr.send(formData);
    xhr.onload = () =>{
        if (xhr.status == 200){
            //console.log("topic create success ! !");
        }
        else{
            //console.error("topic create fail ! !", xhr.statusText);
        }
    };
}

function getUserInfo(kakaoId){
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/getUserInfo/'+kakaoId, true);
    xhr.send();
    xhr.onload = () => {
        if (xhr.status == 200) {
            //console.log("success ! !");
            const data = JSON.parse(xhr.response);
            //console.log(data);
            document.querySelector('#nor').innerHTML = ''+data['nor']; //댓글 수 기입
            document.querySelector('#nov').innerHTML = ''+data['nov']; //투표 수 기입
        } else {
            //console.log("fail ! !");
        }
    }
}