
//Interface that provides a structure for recieving account objects
//this will match/correspond to the object that the server returns  
export interface IAccounts {

id          : number, 
creationTime: string,
balance     : number,
type        : string,

}


/*
    user: [
        id:number, 
        username:string, 
        password:string, 
        firstName:string, 
        lastName:string, 
        email:string, 
        address:string, 
        city:string, 
        state:string, 
        zipCode:string, 
        accounts:[]
    ]
    creationTime: number,
    balance: number,
    user_id: number,
    type: string,
    transaction: []
*/