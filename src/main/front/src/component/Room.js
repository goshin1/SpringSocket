import './room.css'
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom"
import SockJS from "sockjs-client";
import axios from 'axios';



export default function Room(){
    const location = useLocation();
    const [msg, setMsg] = useState("");
    const [block, setBlock] = useState([]);
    const [before, setBefore] = useState([]);

    let sock = new SockJS(`http://localhost:8080/socket`);
    let id = location.state.id;
    let user = location.state.user;

    useEffect(()=>{
        sock.onopen = () =>{
            sock.send(JSON.stringify({chatRoomId : id, type : "JOIN"}));
        }

        axios.get(`${process.env.REACT_APP_HOST}/socket/chatlist`, {
            params : {
                room : id
            }
        })
        .then((response) => {
            console.log(response.data)
            let arr = [];
            for(let i = 0; i < response.data.length; i++){
                arr.push(
                    <div key={response.data[i].id} className="chatBlock">
                        <div className='userB'>
                            User : {response.data[i].user}
                        </div>
                        <div className='msgB'>
                            {response.data[i].message}
                        </div>
                    </div>
                )
            }
            
            setBefore(...arr);
        });
    },[]);

    sock.onmessage = (e) => {
        let content = JSON.parse(e.data);
        let type = content.type;
        if(type == "SEND"){
            setBlock((beforeblock)=>[...beforeblock, e.data])
            console.log(block)
        }
    };

    // axios.get(`${process.env.REACT_APP_HOST}/socket/chatlist`, {
    //     params : {
    //         room : id
    //     }
    // })
    // .then((response) => {
    //     console.log(response.data)
    // });
    

    return <div className='room'>
        <h1>Chatting</h1>
        <div className="chatLog">
            {before}
            {block.map((b, idx) => {
                console.log(b)
                let t = JSON.parse(b);
                console.log(t)
                return <div key={idx} className="chatBlock">
                    <div className='userB'>
                        User : {t.user}
                    </div>
                    <div className='msgB'>
                        {t.message}
                    </div>
                </div>
            })}
        </div>
        <div className='inputBlock'>
            <input type='text' name='text' onChange={(e) => {
                setMsg(e.target.value);
            }}/>
            <input type="button" value="send" onClick={()=>{
                if(msg === "" || msg === undefined)
                    return
                console.log(msg)
                sock.send(JSON.stringify({chatRoomId : id, type : "SEND", message : msg, user : user, room : id}));
                
                axios.get(`${process.env.REACT_APP_HOST}/socket/save`, {
                    params : {
                        room : id,
                        user : user,
                        msg : msg   
                    }
                })
            }}/>
        </div>
    </div>
}