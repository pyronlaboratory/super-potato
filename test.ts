interface NumberGenerator {
    (num: number): number
}
interface GenANum {
    (num: number, gen: NumberGenerator): number
}
const generateANumber: GenANum = (factor: number, genFunc: NumberGenerator) => {
    return genFunc(factor)
}
console.log(generateANumber(5, (a:number) => a)) // 5
console.log(generateANumber(5, () => "Cheese")) // Type Error

interface Item<T> {
  value: T;
}

const stringItem: Item<string> = { value: 'Item' };
const numberItem: Item<number> = { value: 22 };

interface Person {
  name: string;
  
}

interface Index {
  one: string;
  two: number;
  [key: string]: string | number | boolean
}

interface Person {
  readonly name: string;
  age?: string;
}


interface Hobbies {
  hobbies?: string[];
}

interface PersonWithHobbies extends Person, Hobbies {} 


const person: PersonWithHobbies = {
  name: 'Coner',
}
 
function updateName(person: Person) {
  // We can read from 'person.name'.
  console.log(`name has the value '${person.name}'.`); // "name has the value 'Coner'." 
 
  // But we can't re-assign it.
  // 👇 Cannot assign to 'name' because it is a read-only property.
  person.name = "hello";
}
