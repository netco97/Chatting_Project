function createOtherMSG(data) {
    return `
        <div class="flex">
            <div class="bg-gray-300 text-black p-2 rounded-lg max-w-xs">
                <div class="text-sm align-text-bottom text-left">
                    ${data.sender}
                </div>
                    ${data.msg}
                <div class="text-sm w-full align-text-bottom text-right">
                    ${data.date}
                </div>
            </div>
        </div>
    `;
}

function createMyMSG(data) {
    return `
        <div class="flex justify-end">
            <div class="bg-blue-200 text-black p-2 rounded-lg max-w-xs">
                <div class="text-sm align-text-bottom text-right">
                    나
                </div>
                    ${data.msg}
                <div class="text-sm w-full align-text-bottom text-right">
                    ${data.date}
                </div>
            </div>
        </div>
    `;
}

function setMsg(data) {
    const msg = data.kakaoId == kakaoId ? createMyMSG(data) : createOtherMSG(data);
    if(data.isPro == 1){
        const flag = checkProScroll();
        msgProContainer.innerHTML += msg;
        if(flag || data.kakaoId == kakaoId){
            proScrollDown();
        }
        proCount+=1;
    }
    else{
        const flag = checkConScroll();
        msgConContainer.innerHTML += msg;
        if(flag || data.kakaoId == kakaoId){
            conScrollDown();
        }
        conCount+=1;
    }
}

function prependMsg(data){
    const msg = data.kakaoId == kakaoId ? createMyMSG(data) : createOtherMSG(data);
    if(data.isPro == 1){
        const flag = checkProScroll();
        msgProContainer.innerHTML = msg + msgProContainer.innerHTML;
        if(flag){
            proScrollDown();
        }
        proCount+=1;
    }
    else{
        const flag = checkConScroll();
        msgConContainer.innerHTML = msg + msgConContainer.innerHTML;
        if(flag){
            conScrollDown();
        }
        conCount+=1;
    }
}

function msgInitXhr(){
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/msgInitXhr/'+roomId, true);
    xhr.send();
    xhr.onload = () => {
        if (xhr.status == 200) {
            //console.log("message success ! !");
            const data = JSON.parse(xhr.response);
            //console.log(data);
            for (item in data) {
                setMsg(data[item]);
            }
        } else {
            //console.log("fail ! !");
        }
    }
}

function msgScrollXhr(isPro, count){
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/msgScrollXhr/'+roomId+'/'+isPro+'/'+count, true);
    xhr.send();
    xhr.onload = () => {
        if (xhr.status == 200) {
            //console.log("success ! !");
            const data = JSON.parse(xhr.response);
            //console.log(data);
            //console.log(data[0]);
            for (item in data) {
                prependMsg(data[item]);
            }
        } else {
            //console.log("fail ! !");
        }
    }
}

function proScrollDown(){

    sectionA.scrollTop = sectionA.scrollHeight;
}

function checkProScroll() {
    const isAtBottom = sectionA.scrollHeight - sectionA.clientHeight <= sectionA.scrollTop + 1;

    return isAtBottom;
}

function conScrollDown(){
    sectionB.scrollTop = sectionB.scrollHeight;;
}

function checkConScroll() {
    const isAtBottom = sectionB.scrollHeight - sectionB.clientHeight <= sectionB.scrollTop + 1;

    return isAtBottom;
}