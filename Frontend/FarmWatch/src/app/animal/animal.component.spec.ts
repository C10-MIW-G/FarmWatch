import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalOverviewComponent } from './animalOverview.component';

describe('AnimalComponent', () => {
  let component: AnimalOverviewComponent;
  let fixture: ComponentFixture<AnimalOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimalOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnimalOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
