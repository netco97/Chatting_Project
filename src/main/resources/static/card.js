function createHTMLElement(data) {
    return `
    <div class="w-full card" topic-id="${data.topicId}">

    <div class="w-full h-64 bg-gray-300 rounded-lg dark:bg-gray-600 flex items-center">
      <div class="w-full h-64 bg-white dark:bg-zinc-800 h-52 rounded-lg">
        <div class="flex flex-col items-center justify-evenly h-full p-3">
          <h4 class="text-xl font-bold text-navy-700 text-black dark:text-white  text-center mt-3 w-full">
            ${data.topic}
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
                ${(data.pros / (data.pros + data.cons) * 100).toFixed(2)}%
              </p>
              <p class="text-sm font-normal text-gray-600 dark:text-white whitespace-nowrap">찬성</p>
            </div>
            <div class="flex flex-col items-center justify-center">
              <p class="text-2xl font-bold text-navy-700 text-black dark:text-white whitespace-nowrap">
                ${data.population}위
              </p>
              <p class="text-sm font-normal text-gray-600 dark:text-white whitespace-nowrap">인기</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <h1 class="w-56 h-6 mt-4 bg-gray-200 rounded-lg dark:bg-zinc-800 dark:text-white">
      <span class="ml-3 font-bold">${data.date} - ${data.date}</span>
    </h1>
    <p class="w-24 h-6 mt-4 bg-gray-200 rounded-lg dark:bg-zinc-800 text-center">
      <span class="font-bold text-sm dark:text-white">D - ${data.period}</span>
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
const elementContainer = document.getElementById('cardContainer');

dataItems.forEach(itemData => {
    const elementHTML = createHTMLElement(itemData);
    elementContainer.innerHTML += elementHTML;
});

let cards = document.querySelectorAll('.card');

cards.forEach(card => {
    card.addEventListener('click', function(){
        const topic = card.getAttribute('topic-id');
        window.location.href = 'topicRoom?topic='+topic;
    });
});