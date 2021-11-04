export class RegistrationDto {
    public username:string;
    public password:string;
    public email:string;
    public f_name:string;
    public l_name:string;
    public address:string;
    public city:string;
    public state:string;
    public zip_code:string;

    constructor(
        username:string,
        password:string,
        email:string,
        f_name:string,
        l_name:string,
        address:string,
        city:string,
        state:string,
        zip_code:string
        )
        {
            this.username = username;
            this.password = password;
            this.email = email;
            this.f_name = f_name;
            this.l_name = l_name;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zip_code = zip_code;
        }
}
