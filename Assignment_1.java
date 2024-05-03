package assignment1;

import java.util.*;//call(import) the all method in util library for Scanner 

public class Assignment_1 {//Name of class

	public static void main(String[] args) {//The main method
		Scanner input = new Scanner(System.in);//open Scanner (gate) to scan values
		System.out.println("Please enter the number of days that the patient has entered the hospital ");//Enter number of days that the patient entered the hospital
		int number_of_day = input.nextInt();//Save the value in int datatype
		double[][] temperature = new double[number_of_day][];//creat 2D array for the whole programme  
		for (int row = 0; row < temperature.length; row++) {//for loop to save the number of times that nurse read the temperature
			System.out.println("Please enter the number of times that nurse read the temperature in the Celsius in day "
					+ (row + 1));
			int number_of_read = input.nextInt();//Save the value in int datatype
			temperature[row] = new double[number_of_read + 1];//Creat in array number of columns(that entered by user) to put temperature (ragged array)
			temperature[row][0] = number_of_read;//The first column is stabel and constatn cant' change (# of reading)
			for (int column = 1; column < temperature[row].length; column++) {//for loop to read temperature start from column 1 because column 0 is used
				System.out.println(
						"Please enter the temperature " + column + " that was read by the nurse in day " + (row + 1));
				double ch_temperature = input.nextDouble();//ch(mean check if its' accepteable or not')double value because may be the read in double type
				while (ch_temperature < 30 || ch_temperature > 45) {//condition for temperature and keep loop until put the right value accepteable
					System.out.println("Theres' an error in temperature!\nPlease enter the temperature again ");//error message
					ch_temperature = input.nextDouble();//scan another value then check
				}
				temperature[row][column] = ch_temperature;//save the right and final value in the basic array called temperature 
			}
		}
		double[] summary = Summary(temperature);//called Summary method 
		System.out.println("The maximum temperature is:" + summary[0] + "\nThe minimum temperature is:" + summary[1]
				+ "\nThe Average is:" + summary[2]);//print the values 
		int[] be_ab_avg = countbelowAboveAverage(temperature);//below and above average(be_ab_avg) calle the method countbelowAboveAverage
		System.out.println("Total number of reading Below (or equal) average is " + be_ab_avg[0]
				+ "\nTotal number of reading Above average is " + be_ab_avg[1]);//print the result
		double[][] sort_array = sortArray(temperature);//creat 2D array to sort array and call the method 
		System.out.println("After sort array");//print sorted array
		printArray(sort_array);//call method for print
		boolean ch_leave = leaveHospital(sort_array);//method call for leaving hospital
		if (ch_leave == true) {
			System.out.println("Yes he/she can leave the hospital");
		} else
			System.out.println("No he/she cant' leave the hospital");
		input.close();//close Scanner
	}

	public static double[] Summary(double[][] array) {//definition of Summary method return datatype double as array
		double sum = 0, counter = 0;//declare variables for average
		double average , min = array[0][1], max = array[0][1];//declare variable and initialize the maximum and minimum value as example the first item that have array[0][1]
		double[] result = new double[3];//declare array that have the result of 3 value 1-average 2-minimum term 3-maximum term 
		for (int row = 0; row < array.length; row++) {//loop to loop around each row
			for (int column = 1; column < array[row].length; column++) {//loop to loop around each column
				sum += array[row][column];//summation all temperature values to find the average
				counter++;//counter for number of element(number of temperature)
				if (max < array[row][column]) {//if statement for the maximum value
					max = array[row][column];//if the value of next temperature is grater than max change the maximum until the last value
				}
				if (min > array[row][column]) {//if statement that the same useful like above but for finding minimum value
					min = array[row][column];
				}
			}
		}
		average = sum / counter;//find average 
		average = Math.rint(100*average)/100;//make the average in two decimal number after decimal point
		result[0] = max;//the first value for example is the maximum value
		result[1] = min;//the secon is maximum 
		result[2] = average;//the last in result array is average
		return result;//return the result that contains max and min value (theres' no pointer to return more than one parameter)
	}

	public static int[] countbelowAboveAverage(double[][] array) {//method for count the number of temperature below and above the average
		double[] average_arr = Summary(array);//declare array and call the summary method to have the average 
		double average = average_arr[2];//declare and but the average value from previous method
		int t_below = 0, t_above = 0;//first let temperature below and above average be zero
		for (int row = 0; row < array.length; row++) {//for loop to find the # of temperature that are below and above average
			for (int column = 1; column < array[row].length; column++) {
				if (average >= array[row][column]) {//if the temperature is below or equal(as the assignment wants) average increment the number of temperature below the avervage
					t_below++;
				} else {//else increment the opposite (above average)
					t_above++;
				}
			}
		}
		int[] result = new int[2];//like first method creat array result to put the values
		result[0] = t_below;
		result[1] = t_above;
		return result;//return array
	}

	public static double[][] sortArray(double[][] array) {//sort method
		double[][] result = new double[array.length][];//creat 2D array to have a copy from original
		for (int row = 0; row < array.length; row++) {//loop for comparing and sorting in ascending order
			for (int column = 0; column < array[row].length - 1; column++) {
				for (int m = column + 1; m < array[row].length; m++) {//m = column + 1 to compare the next index
					if (array[row][m] < array[row][column]) {//if the second index is greater than the first change 
						double temp = array[row][m];
						array[row][m] = array[row][column];
						array[row][column] = temp;
					}
				}
			}

		}
		System.arraycopy(array, 0, result, 0, array.length);//copy the original array (like what the assignment want)
		return array;//return the copy of sorting array
	}

	public static void printArray(double[][] array) {//method for printing an array
		for (int row = 0; row < array.length; row++) {//Loops to print array 
			System.out.print(array[row][0] + " ");//print the first column that has the number of reading 
			for (int column = 1; column < array[row].length; column++) {
				System.out.print(array[row][column] + " ");
			}
			System.out.println("\n");//after any row print new line 
		}
	}
	public static boolean leaveHospital(double[][] array) {//method to have the OK to leave or not' by finding the average (boolean return type for True or false (Yes or No))
		boolean result;//return variable
		double average = 0;//declare and initialize average
		double sum ;//declare the summation of tepreatures 
		int l_row = array.length-1;//the last row 
		int l_col = (array[array.length-1].length-1);//the last column
		double last_temperature = array[l_row][l_col];//the last value is combination of last row and last column
		int s_l_row = array.length - 2;//second last row = last row - 1
		int s_l_col = ((array[array.length-2].length)-1);//second last column
		double b_last_temperature = array[array.length - 2][(array[array.length-2].length)-1];//befor last tempreature
		double l_temp_b_l_row = array[s_l_row][l_col];//last tempreature before last row (second last) 
		double b_l_temp_b_l_row = array[s_l_row][s_l_col];//before last tempreature before last row
		sum = last_temperature + b_last_temperature + l_temp_b_l_row + b_l_temp_b_l_row;//summation for values
		average = sum / 4.0;//average is divided by number of elements that = 4 
		System.out.println("The Average for last two highest read temperature was = "+average);//print the average
		if (average >= 35.5 && average <= 36.5) {//if statment to find the value of average (if leave or not')
			result = true;//you can leave in main
		} else
			result = false;//you cant
		return result;
	}
}

