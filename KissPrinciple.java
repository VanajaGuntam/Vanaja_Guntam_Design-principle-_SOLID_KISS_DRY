interface Country {
  code: string;
  discountAmountPercent: number;
  taxAmountPercent: number;
  discountRegions: Array<string>;
}

class Poland implements Country {
    code: string = "pl_PL";
    discountAmountPercent: number = 15;
    taxAmountPercent: number = 23;
    discountRegions: Array<string> = [
      "masovia",
      "lubusz"
    ];
}

class Payment {

     setTax(price: any, tax: number) {
       return (price + (tax/100*price));
     }

     setDiscount(price: any, discount: number) {
       return (price - ((discount/100)*price));
     }

     pay(country: Country, region: string, amount: number, nettoPrice: number) {

       if (
         country.discountRegions.indexOf(region.toLowerCase()) != -1
         && amount > 15
       ) {
         nettoPrice = this.setDiscount(nettoPrice, country.discountAmountPercent);
       }

       const bruttoPrice = this.setTax(nettoPrice, country.taxAmountPercent);
       return (bruttoPrice*amount);
     }
}

const payment = new Payment();
console.log ( payment.pay((new Poland), 'masovia', 25, 1000) );
