-- Retrieve managers with base salary > 3000 in October 2025 payroll
SELECT emp.employee_id,emp.first_name || ' ' || emp.last_name FULL_NAME, pay.paid_base_salary, pay.net_pay
FROM EMPLOYEE emp INNER JOIN PAYROLL pay
ON emp.employee_id = pay.employee_id
WHERE emp.JOB_TITLE_ID=7 and pay.paid_base_salary >3000 AND pay.payment_date='31-OCT-25';


-- Retrieve employee IDs, full names, and hire dates for all employees
SELECT emp.employee_id ID
    , emp.first_name || ' ' || emp.last_name FULL_NAME
    , emp.hire_date
FROM EMPLOYEE emp;

-- Retrieve full names of managers only (JOB_TITLE_ID = 7)
SELECT emp.first_name || ' ' || emp.last_name FULL_NAME
FROM EMPLOYEE emp
WHERE emp.job_title_id=7;


-- Retrieve all payroll records where the paid base salary exceeds 4000
SELECT *
FROM PAYROLL pay
WHERE pay.paid_base_salary > 4000;


-- Retrieve employee details and their 'Work' phone numbers
SELECT emp.employee_id
    , emp.first_name || ' ' || emp.last_name FULL_NAME
    , phone.phone_number
FROM EMPLOYEE emp
JOIN EMPLOYEE_PHONE phone
ON emp.employee_id = phone.employee_id
WHERE phone.phone_type='Work';


-- Count the number of employees per department (using department ID)
SELECT emp.department_id
    , COUNT(*) NUMBER_OF_EMPLOYEE
FROM EMPLOYEE emp
GROUP BY emp.department_id
ORDER BY emp.department_id;

-- Retrieve department names and the full name of their respective manager (ordered by manager hire date)
SELECT emp.first_name || ' ' || emp.last_name FULL_NAME
    , dep.name DEPARTMENT_NAME
FROM EMPLOYEE emp JOIN DEPARTMENT dep
ON emp.employee_id = dep.manager_id
ORDER BY emp.hire_date;


-- Count the number of active employees currently assigned to each project
SELECT proj.title
    , COUNT(*) NUMBER_OF_EMPLOYEE
FROM
    EMPLOYEE emp
    JOIN EMPLOYEE_PROJECT empproj
        ON emp.employee_id = empproj.employee_id
    JOIN PROJECT proj
        ON proj.project_id = empproj.project_id
WHERE empproj.status='ACTIVE'
GROUP BY proj.title;


-- Calculate the average bonus amount per department for the 10/2025 payroll
SELECT dep.name DEPARTMENT_NAME
    , ROUND(AVG(pay.bonus_amount),2) AVG_OF_BOUNS_AMOUNT
FROM
    PAYROLL pay
    JOIN EMPLOYEE emp
        ON pay.employee_id = emp.employee_id
    JOIN DEPARTMENT dep
        ON emp.department_id = dep.department_id
WHERE pay.payment_date='31-OCT-25'
GROUP BY dep.name;

-- Count the total number of employees in each department
SELECT dep.name
    , COUNT(*) NUMBER_OF_EMPLOYEE
FROM EMPLOYEE emp
    JOIN DEPARTMENT dep
        ON dep.department_id = emp.department_id
GROUP BY dep.name
ORDER BY dep.name;

-- Count employees who completed a project in 2024 (based on start date filter)
SELECT
    empproj.project_id
    , COUNT(*)
FROM
    EMPLOYEE_PROJECT empproj
WHERE
    empproj.status='COMPLETED'
    AND EXTRACT(YEAR FROM empproj.start_date) = 2024
GROUP BY
    empproj.project_id
ORDER BY
    empproj.project_id;


-- Retrieve the top 5 highest paid employees (base salary) for the 11/2025 payroll
SELECT emp.first_name || ' ' || emp.last_name FULL_NAME
    , pay.net_pay
FROM EMPLOYEE emp
    JOIN PAYROLL pay
        ON emp.employee_id = pay.employee_id
WHERE pay.payment_date= '30-NOV-25'
ORDER BY pay.net_pay DESC
FETCH FIRST 5 ROWS ONLY;


-- Retrieve managers who received a deduction amount greater than 100 in any payment
SELECT emp.first_name || ' ' || emp.last_name FULL_NAME
    , pay.deduction_amount
FROM EMPLOYEE emp
    JOIN PAYROLL pay
        ON emp.employee_id = pay.employee_id
WHERE
    pay.deduction_amount > 100
    AND emp.job_title_id=7;


-- Identify high-activity departments: count active employees and filter departments with more than 2
SELECT dep.name AS DEPARTMENT_NAME
    , COUNT(*) AS ACTIVE_EMPLOYEE_COUNT
FROM DEPARTMENT dep
    JOIN EMPLOYEE emp
        ON dep.department_id = emp.department_id
    JOIN EMPLOYEE_PROJECT empproj
        ON emp.employee_id = empproj.employee_id
WHERE empproj.status = 'ACTIVE'
GROUP BY dep.name
HAVING COUNT(*) > 2
ORDER BY ACTIVE_EMPLOYEE_COUNT DESC;


-- Retrieve employees with NO currently active projects (using the NOT IN method)
SELECT emp.employee_id, emp.first_name || ' ' || emp.last_name FULL_NAME
FROM EMPLOYEE emp
WHERE emp.employee_id NOT IN (
            SELECT DISTINCT employee_id
            FROM EMPLOYEE_PROJECT
            WHERE status= 'ACTIVE'
            )
ORDER BY emp.employee_id;


-- Retrieve employees with NO currently active projects (using the efficient LEFT JOIN IS NULL method)
SELECT emp.employee_id,emp.first_name || ' ' || emp.last_name FULL_NAME
FROM EMPLOYEE emp
    LEFT JOIN EMPLOYEE_PROJECT empproj
        ON  emp.employee_id = empproj.employee_id
        AND empproj.status ='ACTIVE'
WHERE empproj.employee_id IS NULL
ORDER BY emp.employee_id;


-- Retrieve department names and their assigned manager's name (excluding departments with no manager_id set)
SELECT
    dep.name AS "DEPARTMENT NAME",
    NVL(emp.first_name || ' ' || emp.last_name ,NULL)  FULL_NAME
FROM DEPARTMENT dep
    LEFT JOIN EMPLOYEE emp
        ON dep.manager_id = emp.employee_id
WHERE dep.manager_id IS NOT NULL;



-- Retrieve employees whose 10/2025 base salary is above the average base salary for that month (using a subquery)
SELECT emp.employee_id
    , emp.first_name
    , emp.last_name
    , pay.paid_base_salary
FROM EMPLOYEE emp
    JOIN PAYROLL pay
        ON emp.employee_id = pay.employee_id
        AND pay.payment_date = '31-OCT-25'
WHERE pay.paid_base_salary > (
                SELECT AVG(paid_base_salary)
                FROM PAYROLL
                WHERE payment_date = '31-OCT-25');


-- Retrieve employees working in either the 'Marketing' or 'Finance' departments
SELECT emp.employee_id
    , emp.first_name
    , emp.last_name
    , dep.name
FROM EMPLOYEE emp
    JOIN DEPARTMENT dep
        ON emp.department_id = dep.department_id
WHERE dep.name ='Marketing' OR  dep.name ='Finance';


SELECT * FROM EMPLOYEE;