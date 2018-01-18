/*
1) EMPLOYEE 테이블에서 이름(Last Name)에 “hae”를 포함하고 있는 사원들과 같은 부서에서 근무하고 있는 사원의 
EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID 를 출력하라. 
*/

select em.employee_id, em.first_name,em.last_name, em.department_id 
from employees em
where department_id in (select department_id 
                     from (select last_name, department_id 
                           from employees
                           where last_name like '%hae%'))
order by em.employee_id desc;

/*
2) 각 도시(city)별 가장 연봉을 많이 받고 있는 사원의 도시 이름(City), First Name, Last Name, 급여를 조회하라. 
급여 순으로 오름차순 정렬하시오. (1-2.sql)
*/  

select lo.city, em.first_name, em.last_name, em.salary
from employees em,departments de, locations lo
where em.department_id = de.department_id and de.location_id = lo.location_id 
      and (city, em.salary) in (select lo.city, max(em.salary)
                                  from locations lo, departments de, employees em
                                  where em.department_id = de.department_id and de.location_id = lo.location_id
                                  group by lo.city)                                                                          
order by em.salary asc;


