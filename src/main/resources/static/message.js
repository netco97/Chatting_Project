function createOtherMSG(data) {
    return `
        <div class="flex">
            <div class="bg-gray-300 text-black p-2 rounded-lg max-w-xs">
                <div class="text-sm align-text-bottom text-left">
                    ${data.name}
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
                    ${data.sendor}
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
        msgProContainer.innerHTML += msg;
    }
    else{
        msgConContainer.innerHTML += msg;
    }
}

function msgInitXhr(){
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/msgInitXhr/'+roomId, true);
    xhr.send();
    xhr.onload = () => {
        if (xhr.status == 200) {
            console.log("success ! !");
            const data = JSON.parse(xhr.response);
            for (item in data) {
                setMsg(item);
            }
        } else {
            console.log("fail ! !");
        }
    }
}