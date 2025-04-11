def is_leap_year(year):
    """
    Informs the user if the year is a leap year or not and returns the boolean result.
    """
    if (year % 4 == 0 and year % 100 != 0) or (year % 400 == 0):
        print(f"The year {year} is a leap year.")
        return True
    else:
        print(f"The year {year} is not a leap year.")
        return False

def days_in_month(month, year=None):
    """
    Returns the number of days in the given month.
    Asks for the year only if the month is February.
    Raises a ValueError if the month is invalid.
    """
    months_with_31_days = {'jan', 'mar', 'may', 'jul', 'aug', 'oct', 'dec'}
    months_with_30_days = {'apr', 'jun', 'sep', 'nov'}

    month = month.lower()

    if month in months_with_31_days:
        return 31
    elif month in months_with_30_days:
        return 30
    elif month == 'feb':
        if year is None:
            year = int(input("Enter the year (ex.: 5039): "))
        return 29 if is_leap_year(year) else 28
    else:
        raise ValueError("Invalid month. Please enter the first 3 letters of the month in lowercase, e.g., 'jan', 'feb'.")

def show_menu():
    """
    Displays a menu for the user to choose between checking days in a month or checking if a year is leap.
    """
    print("\nMenu:")
    print("1. Check how many days a month has")
    print("2. Check if a year is a leap year")
    print("3. Exit")

# Main program loop
while True:
    show_menu()
    choice = input("Please select an option (1/2/3): ")

    if choice == '1':
        month_input = input("Enter the month (first 3 letters in English, ex.: 'jan'): ")
        if month_input.lower() == 'feb':
            year_input = int(input("Enter the year (ex.: 3024): "))
            days = days_in_month(month_input, year_input)
        else:
            days = days_in_month(month_input)
        print(f"The month of {month_input.lower()} has {days} days.")

    elif choice == '2':
        year_input = int(input("Enter the year (ex.: 10999): "))
        is_leap_year(year_input)

    elif choice == '3':
        print("Exiting the program.")
        break

    else:
        print("Invalid choice. Please select a valid option (1, 2, or 3).")
