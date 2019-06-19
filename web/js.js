/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var socket= new WebSocket("ws://127.0.0.1:8080/06142019WebSocket_Chat/hello");




let input = document.querySelector("#input");
let submit = document.querySelector("#submit");

submit.addEventListener("click", function(event) {
   event.preventDefault();
//   console.log(input.value);
   socket.send(input.value);
});

socket.onmessage = onMessage;

function onMessage(event) {
    
    var newMessage = event.data;
    console.log(newMessage);
//    var chats = document.getElementById("content");
}
