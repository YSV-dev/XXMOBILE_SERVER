package com.uralkali.server.services.data;

import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.utils.MoreObjects;
import com.uralkali.common.models.dto.filters.ProblemFilter;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.server.models.entities.ProblemEntity;
import com.uralkali.server.utils.Json;
import com.uralkali.server.utils.Resources;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class ProblemApi {
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(3)) {
            case "" :
                result = getProblems(webRequest, router);
                break;
            case "add" :
                result = addProblem(webRequest, router);
                break;
            /*case "status" :
                result = status(webRequest, router);
                break;*/
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }

    private  static int getProblems(WebRequest webRequest, ClientRouter router) {
        ProblemFilter filter = null;
        try {
            filter = (ProblemFilter) Json.fromJson(webRequest.getPostAsText(), ProblemFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex);
        }

        Session session = router.getSession();

        String sql = Resources.getAsString("/sql/list/problems.sql");

        String conditions = " ";
        HashMap<String, Object> params = new HashMap<>();


        if(filter.getProblemId() != null) {
            params.put("p_problem_id", filter.getProblemId());
            conditions += "and PROBLEM_ID = :p_problem_id ";
        } else {

            Long timeFrom = filter.getDateFrom();
            if(timeFrom != null) {
                timeFrom = timeFrom - TimeZone.getDefault().getRawOffset();
            }

            Long timeTo = filter.getDateTo();
            if(timeTo != null) {
                timeTo = timeTo - TimeZone.getDefault().getRawOffset();
            }

            if(filter.getOrganizationId()!= null) {
                params.put("p_organization_id", filter.getOrganizationId());
                conditions += "and ORGANIZATION_ID = :p_organization_id ";
            }

            if(filter.getDateFrom() != null) {
                params.put("p_date_from", new Timestamp(timeFrom) );
                conditions += "and CREATION_DATE >= :p_date_from ";
            }
            if(filter.getDateTo() != null) {
                params.put("p_date_to", new Timestamp(timeTo) );
                conditions += "and CREATION_DATE <= :p_date_to ";
            }

            if(filter.getName()!= null) {
                params.put("p_name", filter.getName() );
                conditions += "and NAME <= :p_name ";
            }
        }

        sql = sql.replace("/*%CONDITIONS%*/", conditions);

        String sortOrder = " ";
        if("asc".equals(filter.getSortingOrder())) {
            sortOrder = " asc ";
        } else if("desc".equals(filter.getSortingOrder())) {
            sortOrder = " desc ";
        }

        if("CREATION_DATE".equals(filter.getSorting())) {
            sql = sql.replace("/"+"*%SORTING%*"+"/", "order by CREATION_DATE" + sortOrder);
        } else {
            sql = sql.replace("/"+"*%SORTING%*"+"/", "order by PROBLEM_ID" + sortOrder);
        }

        //sql = sql.replace(":p_limit", String.valueOf(limit));

        Query<ProblemEntity> query = session.createNativeQuery(sql, ProblemEntity.class);

        for(String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }

        Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
        query.setParameter("p_limit", limit);

        List<ProblemEntity> data = query.getResultList();

        String json = Json.toJson(data);

        WebResponse response = new WebResponse(webRequest);
        response.setContentType("application/json");
        response.send(json);
        response.sendHeaders(200);
        return 200;
    }

    /**
     * Добавление новой проблемы.
     */
    private static int addProblem(WebRequest webRequest, ClientRouter router) {
        return 1;
    }
}
