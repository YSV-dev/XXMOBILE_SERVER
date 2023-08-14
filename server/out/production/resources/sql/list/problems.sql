select
    PROBLEM_ID,
    ORGANIZATION_ID,
    NAME,
    DESCRIPTION,
    CREATION_DATE,
    CREATED_BY
from XXMOBILE_PROBLEMS
where 1=1
    /*%CONDITIONS%*/
/*%SORTING%*/
fetch first :p_limit rows only