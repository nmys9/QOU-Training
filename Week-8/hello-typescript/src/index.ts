// let x:number=5;
// let y:number=3;
// let sum:number=x+y;
// let result:string=``;
// console.log(sum);
// console.log(Math.floor(5));
//
// let name=33
//
// name = 3;
//
// function add(n1:number,n2:number){
//     return n1+n2;
// }
//
// let all:string| number|boolean=3;
//
// all="noor";

// let myFriends:string[]=["noor","Shahd"];
//
// for (let i=0; i<myFriends.length;i++){
//     console.log(myFriends[i].repeat(2));
// }



// let arrayOne:number[]=[1,2,3,4];
// let arrayTwo:string[]=["noor","Shahd"];
//
// let arrayThree:(number[] | string[])[]=[
//     [1,2,3,4],
//     ["S","s"]
// ];
// console.log(arrayOne);
//
// function fun(name:string,_age:number){
//     console.log(`name is ${name} and age Is`);
// }
//
// fun("noor",9);
//
// function showData(name:string,age:number, country?:string){
//     return `${name} - ${age} - ${country}`;
// }
//
// console.log(showData("noor",3));




// function addAll(...nums:number[]):number {
//     let result:number=0;
//     // for (let i = 0; i < nums.length; i++) {
//     //     result += nums[i]!;
//     // }
//     nums.forEach((num)=> result += num);
//     return  result;
// }
//
// console.log(addAll(10,20,30,100,10.8,+true));
//
//
// const add=function (n1:number,n2:number):number{
//     return n1+n2;
// }
//
// console.log(add(2,3));
//
//
// const addWithArrow=(n1:number,n2:number):number => n1+n2;
// console.log(addWithArrow(2,3));


//
// type Id=number | string;
//
// let x:Id = 4;
// let y:Id = "noor";
//

//
// type User={
//     name:string,
//     age:number,
//     isAdmin:boolean
// };
//
// let user1:User={
//     name: "noor",
//     age:23,
//     isAdmin:true
// };
//
//
// type Numbers=number[];
//
// let array:Numbers=[1,2,34,4];
//
// type AddFn=(a :number,b:number) => number;
//
// const add:AddFn=(x, y) => x+y;
//
// console.log(add(3,9));
//
//
// type Status ="Success" | "error" | "loading";
//
// let currentStatus:Status;
//
// currentStatus="Success";
//
//
//
// console.log(user1.age);
//
// let email:string | null | undefined;
//
// email="noor";
//
// email=undefined;
//
//
//
// let scores:number[]=[2,3,4,5,6];
// let highScores:number[];
//
// highScores=scores.filter(score => score>3);
//
// console.log(highScores);
//
//
// let myFun=(message:string) => console.log(message);
//
// myFun("noor");
//
// let logger:(message:string) => void;
//
//
//
// let art: readonly[number,string,boolean] =[1,"noor",true];
//
// console.log(art);
// const [id,name,isUser] = art;
// console.log(name);


// const enum Level{
//     Kids=15,
//     Easy=9,
//     Medium=6,
//     Hard=3
// }
//
// let lvl:string= "Easy";
//
// if (lvl === "Easy"){
//     console.log(`The Level is ${lvl} and number of seconds is ${Level.Easy}`);
// }
//
//


// let myImg=document.getElementById("my-img") as HTMLImageElement;
// let myImg= <HTMLImageElement> document.getElementById("my-img");
// console.log(myImg.src);


// type Buttons={
//     up:string,
//     right:string,
//     down:string,
//     left:string
// };
//
// function getActions({up,right,down,left}: Buttons){
//     console.log(`Action for button up is ${up}`);
//     console.log(`Action for button right is ${right}`);
//     console.log(`Action for button down is ${down}`);
//     console.log(`Action for button left is ${left}`);
// }
//
// getActions({up:"Up",right:"Right",down:"Down",left:"Left"});
//
//
//
//
// interface User{
//     id?:number,
//     username:string,
//     country:string,
//     sayHello():string,
//     sayWelcome: () => string,
//     getDouble(n1:number, n2:number): number
// }
//
// let user:User ={
//     username:"noor",
//     country:"Nabuls",
//     sayHello(): string {
//         return  `Hello ${this.username}`;
//     } ,
//     sayWelcome: () => `Welcome ${user.username}`,
//     getDouble(n1: number, n2: number): number {
//         return n1+n2;
//     }
// };
//
// console.log(user);
// console.log(user.sayHello());
// console.log(user.sayWelcome());
// console.log(user.getDouble(1,2));
// function getData({username,country}:User){
//     // console.log(`Id is ${id}`);
//     console.log(`username is ${username}`);
//     console.log(`country is ${country}`);
// }
//
// // getData({username: "noor",country: "Nablus"});
//
//
//
//


