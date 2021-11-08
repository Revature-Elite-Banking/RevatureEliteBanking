export class RegistrationDto {
    public username:string;
    public password:string;
    public email:string;
    public firstName:string;
    public lastName:string;
    public address:string;
    public city:string;
    public state:string;
    public zipCode:string;

    constructor(
        username:string,
        password:string,
        email:string,
        firstName:string,
        lastName:string,
        address:string,
        city:string,
        state:string,
        zipCode:string
        )
        {
            this.username = username;
            this.password = password;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        }
}
