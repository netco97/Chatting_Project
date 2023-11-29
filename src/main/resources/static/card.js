function createCard(data, deadline, dday) {
    //console.log(data);
    return `
    <div class="w-full card" topic-id="${data.roomId}" onclick="window.location.href='topicRoom?roomId='+'${data.roomId}'">

    <div class="w-full h-64 bg-gray-300 rounded-lg dark:bg-gray-600 flex items-center">
      <div class="w-full h-64 bg-white dark:bg-zinc-800 h-52 rounded-lg">
        <div class="flex flex-col items-center justify-evenly h-full p-3">
          <h4 class="text-xl font-bold text-navy-700 text-black dark:text-white  text-center mt-3 w-full">
            ${dday[1] == '+' ? '(만료)' : ''}${data.topic}
          </h4>
          <div class="w-full h-6 bg-gray-200 rounded-full dark:bg-gray-700">
            <div class="h-6 bg-blue-600 rounded-full dark:bg-blue-500" style="width: ${(data.pros / (data.pros + data.cons) * 100).toFixed(2)}%;"></div>
          </div>
          <div class="mt-6 mb-3 w-full flex justify-around">
            <div class="flex flex-col items-center">
              <p class="text-2xl font-bold text-navy-700 text-black dark:text-white whitespace-nowrap">${data.reply}</p>
              <p class="text-sm font-normal text-gray-600 dark:text-white whitespace-nowrap">댓글 수</p>
            </div>
            <div class="flex flex-col items-center justify-center">
              <p class="text-2xl font-bold text-navy-700 text-black dark:text-white whitespace-nowrap">
                ${data.pros == 0 ? '0' : (data.pros / (data.pros + data.cons) * 100).toFixed(2)}%
              </p>
              <p class="text-sm font-normal text-gray-600 dark:text-white whitespace-nowrap">찬성</p>
            </div>
            <div class="flex flex-col items-center justify-center">
              <p class="text-2xl font-bold text-navy-700 text-black dark:text-white whitespace-nowrap">
                ${(data.pros + data.cons)}
              </p>
                        <p class="text-sm font-normal text-gray-600 dark:text-white whitespace-nowrap">투표수</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <h1 class="w-56 h-6 mt-4 bg-gray-200 rounded-lg dark:bg-zinc-800 dark:text-white">
                <span class="ml-3 font-bold">${data.date} - ${deadline}</span>
              </h1>
              <p class="w-24 h-6 mt-4 bg-gray-200 rounded-lg dark:bg-zinc-800 text-center">
                <span class="font-bold text-sm dark:text-white">${dday}</span>
              </p>
            </div>
              `;
          }

          // 생성할 데이터 배열
          const dataItems = [
              { topicId: 'ABCD', topic: 'topic 1', reply: 1234, pros: 232, cons: 123, population: 2, date: '2023.11.25', period: 15 },
              { topicId: 'EFGH', topic: 'topic 2', reply: 124, pros: 23, cons: 82, population: 3, date: '2023.11.21', period: 12 },
              { topicId: 'IJKL', topic: 'topic 3', reply: 12324, pros: 9312, cons: 1243, population: 1, date: '2023.11.23', period: 1 },
          ];



          // 데이터 배열을 순회하면서 HTML 요소 생성 후 컨테이너에 추가
          const cardContainer = document.getElementById('cardContainer');
          /*
          dataItems.forEach(itemData => {
              const elementHTML = createHTMLElement(itemData);
              elementContainer.innerHTML += elementHTML;
          });

          let cards = document.querySelectorAll('.card');

          cards.forEach(card => {
              card.addEventListener('click', function(){
                  const topic = card.getAttribute('topic-id');
                  window.location.href = 'topicRoom.html?topic='+topic;
              });
          });
          */
function setCards(data){
   for(item in data){
      const card = createCard(data[item], addDays(data[item].date, data[item].period), getDday(addDays(data[item].date, data[item].period)));
      cardContainer.innerHTML += card;
   }
}

function getRoomInformations(){
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/information', true);
    xhr.send();
    xhr.onload = () => {
        if (xhr.status == 200) {
            //console.log("success ! !");
            let data = JSON.parse(xhr.response);
            setCards(data);
        } else {
            //console.log("fail ! !");
        }
    }
}

function addDays(date, period) {
    var newDate = new Date(date);
    newDate.setDate(newDate.getDate() + period);
    //console.log(newDate.getFullYear());
    return newDate.getFullYear() + '-' + (newDate.getMonth() + 1).toString().padStart(2, '0') + '-' + newDate.getDate().toString().padStart(2, '0');
}

function getDday(date) {
    // 목표 날짜를 Date 객체로 변환
    var targetDate = new Date(date);
    targetDate.setHours(0, 0, 0, 0); // 시간, 분, 초, 밀리초를 0으로 설정

    // 현재 날짜
    var today = new Date();
    today.setHours(0, 0, 0, 0); // 시간, 분, 초, 밀리초를 0으로 설정

    // 디데이 계산
    var diff = targetDate - today;
    var dDay = diff / (1000 * 60 * 60 * 24);

    // 디데이 문자열 포맷팅
    if (dDay > 0) {
        return "D-" + Math.floor(dDay); // 디데이까지 남은 일수
    } else if (dDay < 0) {
        return "D+" + Math.abs(Math.ceil(dDay)); // 디데이가 지난 일수
    } else {
        return "D-Day"; // 오늘일 경우
    }
}
