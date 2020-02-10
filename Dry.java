enum dayNames {
  Monday = "Monday",
  Tuesday = "Tuesday",
  Wednesday = "Wednesday",
  Thursday = "Thursday",
  Friday = "Friday",
  Saturday = "Saturday",
  Sunday = "Sunday"
}

class Day {
  name: string;
  order: number;

  constructor(name: string, order: number = 0) {
    this.name = name;
    this.order = order;
  }

  setOrder(order: number) : Day {
    this.order = order;
    return this;
  }

}

class Week {

  days: Array<Day> = new Array();

  private addDay(name: string): Day {
    const day = new Day(name);
    const index = this.days.push(day);
    day.setOrder(index)
    return day;
  }

  constructor() {
     for(let dayName in dayNames) {
       this.addDay(dayName);
     }
  }

  listDays() {
    console.log(this.days);
  }

}

const firstWeek = new Week();
firstWeek.listDays();
