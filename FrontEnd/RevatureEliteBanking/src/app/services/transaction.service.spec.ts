import { TestBed } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { TransactionService } from './transaction.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {FormsModule} from '@angular/forms';

describe('TransactionService', () => {
  let service: TransactionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, FormsModule],
    });
    service = TestBed.inject(TransactionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

describe("findAccountTransaction2", () => {
  let i:any
  let service: TransactionService;
  console.log("asdjashjaqgfjabgvsfkjabsgdf")
  
  

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, FormsModule],
    });
    service = TestBed.inject(TransactionService);
    
  });

  it('should return 1', ()=> {
    const transactionTest = service.findAccountTransactions2(1);
    expect(transactionTest).toBe(1)
  })
})
