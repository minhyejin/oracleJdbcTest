/*
1)	평균 연봉(salary)이 가장 높은 나라는?
 */
select co.country_name as 나라이름, se.avg1 as 평균임금
from employees em, departments de, locations lo, countries co,(select co.country_name, avg(em.salary)avg1 
                                                               from employees em,departments de, locations lo, countries co
                                                               where em. department_id = de.department_id 
                                                                     and de.location_id = lo.location_id 
                                                                     and lo.country_id = co.country_id
                                                               group by co.country_name
                                                               order by avg1 desc)se
where rownum <=1;

/*
2)	평균 연봉(salary)이 가장 높은 지역은?
*/

select re.region_name 나라이름 , se.avg1 as 평균임금
from employees em, departments de, locations lo, countries co, regions re , (select re.region_name, avg(em.salary)avg1
                                                                             from employees em, departments de, 
                                                                                  locations lo, countries co, regions re
                                                                             where em.department_id = de.department_id and 
                                                                                   de.location_id = lo.location_id and 
                                                                                   lo.country_id = co.country_id and
                                                                                   co.region_id = re.region_id 
                                                                            group by re.region_name
                                                                            order by avg1 desc)se
where rownum <=1;
/*
3)	가장 많은 직원이 있는 부서는 어떤 부서인가요?
*/
select de.department_name, cnt
from employees em, departments de, (select count(em.employee_id)cnt, department_name 
                                    from employees em, departments de 
                                    where em.department_id = de.department_id 
                                    group by department_name
                                    order by cnt desc)se
where rownum <=1;

