// class Usser{
//     static get created(): number {
//         return this._created;
//     }
//
//     static set created(value: number) {
//         this._created = value;
//     }
//     get address(): string {
//         return this._address;
//     }
//     get salary(): number {
//         return this._salary;
//     }
//
//     set salary(value: number) {
//         this._salary = value;
//     }
//     get username(): string {
//         return this._username;
//     }
//
//     set username(value: string) {
//         this._username = value;
//     }
//     private static _created:number=0;
//     static getCount(){
//         console.log(`${Usser._created}`);
//     }
//
//     msg:()=> string;
//
//     constructor(private _username: string, private _salary: number, readonly _address: string){
//         Usser._created++;
//         this.msg=function (){
//             return `Hello ${this._username} your salary is ${ this._salary}`;
//         }
//     }
//     sayMsg(){
//         return `Hello ${this._username} your salary is ${this._salary}`;
//     }
//     // get username():string{
//     //     return this._username;
//     // }
//     // set username(username:string):void{
//     //     this._username=username;
//     // }
//
// }
//
//
// let userOne=new Usser("Noor", 1000, "s");
// console.log(userOne);
// console.log(userOne.username);
// userOne.username="nnnnn";
// console.log(userOne.username);
// console.log(userOne.salary);
// let userTwo=new Usser("noor",1222,"w");
// console.log(Usser.created);
// Usser.getCount();
//
//
//
//
//
// interface Settings{
//     theme:boolean,
//     font:string,
//     save():void
// }
//
//
// class MyUser implements Settings{
//     constructor(public username:string,public theme:boolean,public font:string) {
//     }
//     save():void{
//         console.log(`Saved`);
//     }
// }
//
//

//
// abstract class Food{
//     constructor(public title:string) {
//     }
//     abstract getCookingTime():void;
// }
//
// class Pizza extends Food{
//     getCookingTime() {
//         console.log(this.title);
//     }
// }
//
//
// let myPizza=new Pizza("Pizza");
// myPizza.getCookingTime();
//
//
//
// class One{
//     myOneClass(name:string):void{
//         console.log(name);
//     }
// }
//
// class Two{
//     myOneClass(name:string):void{
//         console.log(name);
//     }
// }



function returnType<T>(val:T):T{
    return val;
}

console.log(returnType(true));
console.log(returnType(33));
console.log(returnType<string>("noor"));
console.log(returnType<number[]>([1,2,3,4]));


function addValue<T,E>(num1:T, num2:E):void{
    console.log(num1);
    console.log(num2);
}
addValue<number,boolean>(2,true);


class User<E,T=boolean>{
    constructor(public value:T) {}
    show(msg:E):void{
        console.log(`${msg} -- ${this.value}`);
    }
}

let userOne=new User("noor");
console.log(userOne.value);
userOne.show("Message");


let userTwo=new User(99);
console.log(userTwo.value);
userTwo.show(88);


















