import { LoginDto } from './login-dto';

describe('LoginDto', () => {
  it('should create an instance', () => {
    expect(new LoginDto("dan", "123")).toBeTruthy();
  });
});
