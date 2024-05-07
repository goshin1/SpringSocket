import "./home.css";
import axios from "axios";
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";

export default function Home(){
    // useEffect(()=>{
    //     axios.get("http://localhost:8080/socket/test")
    //         .then((response) => {
    //             console.log(response)
    //         })
    // },[]);

    const navigate = useNavigate();
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_HOST}/socket/home`)
            .then((response) => {
                console.log(response.data);
                let arr = [];
                for(let i = 0; i < response.data.length; i++){
                    arr.push(
                        <div key={response.data[i].id} className="roomLink" onClick={() => {
                            let user = document.getElementById("user").value;
                            console.log("user : "+user)
                            if(user === "" | user === undefined)
                                return;
                            navigate('/room', {
                                state : {
                                    id : response.data[i].id,
                                    user : user
                                }
                            })
                        }}>
                            {/* <a id={"room"+i} key={response.data[i]} href={process.env.REACT_APP_HOST+"/room/"+response.data[i].id}>
                                
                                {i + 1}번방 입장
                            </a> */}
                            {i + 1}번방 입장
                        </div>
                    )
                }
                setRooms([...arr]);
            })
    }, []);




return <div className="home">
    <h1>home</h1>
    {rooms}
    <div>
        <input type='text' id='user' name='user' placeholder="user name"/>
    </div>
</div>
}