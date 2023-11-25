import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import axios from "axios";

<<<<<<< HEAD
const LoginHandeler = (props) => {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get("code");

  console.log(code);
=======
const LoginHandler = (props) => {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get("code");

>>>>>>> origin/master
//인가코드 백으로 보내는 코드
  useEffect(() => {
    const kakaoLogin = async () => {
      await axios({
        method: "GET",
<<<<<<< HEAD
        url: `/login/kakao?code=${code}`,
=======
        url: `login/oauth/kakao?code=${code}`,
>>>>>>> origin/master
        headers: {
          "Content-Type": "application/json;charset=utf-8", //json형태로 데이터를 보내겠다는뜻
          "Access-Control-Allow-Origin": "*", //이건 cors 에러때문에 넣어둔것. 당신의 프로젝트에 맞게 지워도됨
        },
      }).then((res) => { //백에서 완료후 우리사이트 전용 토큰 넘겨주는게 성공했다면
        console.log(res);
        //계속 쓸 정보들( ex: 이름) 등은 localStorage에 저장해두자
<<<<<<< HEAD
        localStorage.setItem("name", res.data.account.kakaoName);
        //로그인이 성공하면 이동할 페이지
        navigate("/owner-question");
      }).catch((error)=>{
      console.log("ERROR" + error)});
=======

        //로그인이 성공하면 이동할 페이지
        navigate("http://localhost:3000");
      }).catch(error => {
            console.log('error!!! : ' + error);
            console.log('url : ' + `${process.env.REACT_APP_REDIRECT_URL}?code=${code}`);
       });
>>>>>>> origin/master
    };
    kakaoLogin();
  }, [props.history]);

  return (
<<<<<<< HEAD
    <div className="LoginHandeler text-white">
=======
    <div className="LoginHandler">
>>>>>>> origin/master
      <div className="notice">
        <p>로그인 중입니다.</p>
        <p>잠시만 기다려주세요.</p>
        <div className="spinner"></div>
      </div>
    </div>
  );
};

<<<<<<< HEAD
export default LoginHandeler;
=======
export default LoginHandler;
>>>>>>> origin/master
