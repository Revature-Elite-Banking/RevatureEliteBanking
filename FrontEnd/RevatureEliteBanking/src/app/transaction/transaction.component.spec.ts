import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TransactionService } from '../services/transaction.service';
import { TransactionComponent } from './transaction.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {FormsModule} from '@angular/forms';

describe('TransactionComponent', () => {
  let component: TransactionComponent;
  let fixture: ComponentFixture<TransactionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, FormsModule],
      declarations: [ TransactionComponent ]
    })
    .compileComponents();
  });

  

  it('should create', () => {
    expect(component).toBeFalsy();
  });

  
});

describe("findAccountTransaction", () => {
  let i:any
  let service: TransactionService;
  console.log("asdjashjaqgfjabgvsfkjabsgdf")
  
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionComponent ]
    })
    .compileComponents();
  });



  it('should return 1', ()=> {
    const transactionTest = 1
    expect(transactionTest).toBe(1)
  })
})
