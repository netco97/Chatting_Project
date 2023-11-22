import axios from "axios";

let data = [

    {
        id : '000000',
        topic : '단칸방 안에 틀어박혀 있는 만25세, 이대로 괜찮은가',
        pros : 4232,
        cons : 5311,
        reply : 13024,
        date : '2023-09-30',
        period : 7,
        isOpened : true
    },
    {
        id : '000001',
        topic : '비트코인 2024년 1억 가능?',
        pros : 8292,
        cons : 3215,
        reply : 20021,
        date : '2023-09-30',
        period : 30,
        isOpened : true
    },
    {
        id : '000002',
        topic : '식용 곤충 우리의 미래인가',
        pros : 53,
        cons : 421,
        reply : 253,
        date : '2023-10-01',
        period : 7,
        isOpened : true
    },
    /*
    {
        id : '000003',
        topic : '월 800 건물주 남편 밤낮 없이 게임만.. 이혼 사유인가?',
        pros : 1242,
        cons : 3754,
        reply : 6420,
        date : '2023-09-26',
        period : 14,
        isOpened : true
    },
    {
        id : '000004',
        topic : '이재명 민주당대표 구속 타당한가',
        pros : 8123,
        cons : 8048,
        reply : 30126,
        date : '2023-09-20',
        period : 20,
        isOpened : true
    },
    {
        id : '000005',
        topic : '메타버스, 우리의 미래인가',
        pros : 3265,
        cons : 4052,
        reply : 8401,
        date : '2023-09-20',
        period : 21,
        isOpened : true
    },
    {
        id : '000006',
        topic : '휴일 근무 중인 8급 공무원이 사무실에서 맥주를 마셔도 괜찮은가?',
        pros : 3215,
        cons : 3816,
        reply : 8907,
        date : '2023-09-26',
        period : 14,
        isOpened : true
    },*/
]

axios.get('api/information')
  .then((res)=>{
        var jsonArr = JSON.parse(res);
        console.log(jsonArr);
     }
  )
  .catch((Error)=>{console.log(Error)})


export default data;