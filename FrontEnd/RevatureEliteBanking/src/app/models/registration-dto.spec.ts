import { RegistrationDto } from './registration-dto';

describe('RegistrationDto', () => {
  it('should create an instance', () => {
    expect(new RegistrationDto("dan", "123","d@y.com","dan","fisher","her","t","nt", "12343")).toBeTruthy();
  });
});
