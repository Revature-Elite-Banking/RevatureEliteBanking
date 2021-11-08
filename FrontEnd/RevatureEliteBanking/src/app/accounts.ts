
//Interface that provides a structure for recieving account objects
//this will match/correspond to the object that the server returns  
export interface IAccounts {
id          : number, 
creationTime: string,
balance     : number,
type        : string,
}