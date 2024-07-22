/**
 * @description Expects a `number` parameter and returns a `number`.
 */
interface NumberGenerator {
    (num: number): number
}
/**
 * @description Takes a `num` parameter of type `number`, and a `gen` parameter of
 * type `NumberGenerator`. The return type is also `number`.
 */
interface GenANum {
    (num: number, gen: NumberGenerator): number
}
/**
 * @description Takes a `factor` and a `genFunc` as input, where `genFunc` is a
 * function that generates random numbers, and returns the generated number.
 * 
 * @param {number} factor - Used to specify the base for the generated number.
 * 
 * @param {NumberGenerator} genFunc - Used to generate random numbers within a specified
 * range or distribution.
 * 
 * @returns {number} Generated using a supplied function `genFunc`.
 */
const generateANumber: GenANum = (factor: number, genFunc: NumberGenerator) => {
    return genFunc(factor)
}
console.log(generateANumber(5, (a:number) => a)) // 5
console.log(generateANumber(5, () => "Cheese")) // Type Error
/**
 * @description Declares a generic type parameter `T`, which represents the data type
 * of the value stored in the interface. The interface defines two properties, `value`
 * of type `T`, and is intended to represent a container for any type of data.
 */
interface Item<T> {
  value: T;
}

const stringItem: Item<string> = { value: 'Item' };
const numberItem: Item<number> = { value: 22 };

/**
 * @description Defines a simple data type that represents a person. It consists of
 * two properties: `name`, which is a string, and no other properties.
 */
interface Person {
  name: string;
  
}

/**
 * @description Defines a data structure with three properties: `one`, `two`, and a
 * key-value pair (`[key: string]: string | number | boolean`). The `one` property
 * is of type `string`, while the `two` property is of type `number`. The key-value
 * pair allows for any type of value to be stored, including strings, numbers, and booleans.
 */
interface Index {
  one: string;
  two: number;
  [key: string]: string | number | boolean
}

/**
 * @description Declares two properties: `name`, a read-only `string` property, and
 * `age`, an optional `string` property with the `?` symbol indicating that it may
 * be null or undefined.
 */
interface Person {
  readonly name: string;
  age?: string;
}


/**
 * @description Declares a property `hobbies` of type `string[]`. This means that the
 * `hobbies` property is an array of strings. The `?` symbol after `hobbies` indicates
 * that the property is optional, meaning it can be present or absent in the object.
 */
interface Hobbies {
  hobbies?: string[];
}

/**
 * @description In TypeScript extends both the `Person` and `Hobbies` interfaces.
 * This means that any instance of `PersonWithHobbies` must also implement the methods
 * and properties defined by both `Person` and `Hobbies`. Additionally, this interface
 * adds its own set of required or optional properties and methods specific to hobbies,
 * such as `hobbyName`, `hobbyLevel`, and `hobbyDaysPerWeek`. By combining these two
 * interfaces, the `PersonWithHobbies` interface provides a more comprehensive
 * representation of an individual with various hobbies.
 */
interface PersonWithHobbies extends Person, Hobbies {} 


const person: PersonWithHobbies = {
  name: 'Coner',
}
 
/**
 * @description Updates the `name` property of the `Person` object by logging its
 * current value and displaying an error message when attempting to re-assign a new
 * value.
 * 
 * @param {Person} person - Used to represent an object containing the name property
 * that can be read but not assigned to.
 */
function updateName(person: Person) {
  // We can read from 'person.name'.
  console.log(`name has the value '${person.name}'.`); // "name has the value 'Coner'." 
 
  // But we can't re-assign it.
  // 👇 Cannot assign to 'name' because it is a read-only property.
  person.name = "hello";
}
