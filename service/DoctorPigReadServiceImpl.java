
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import io.terminus.common.model.PageInfo;
import io.terminus.common.model.Paging;
import io.terminus.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * Desc: 猪基础信息表 service
 * Mail: yaoqj@terminus.io
 * author: yaoqijun
 * Date: 2016-05-21
 */
@Slf4j
@Service
public class DoctorPigReadServiceImpl implements DoctorPigReadService{

    private final DoctorPigDao DoctorPigDao;

    public DoctorPigReadServiceImpl(DoctorPigDao DoctorPigDao){
        this.DoctorPigDao = DoctorPigDao;
    }

    @Override
    public Response<DoctorPig> queryById(@NotNull(message = "input.id.empty") Long id) {
        try{
            return Response.ok(DoctorPigDao.findById(id));
        }catch (Exception e){
            log.error("query by id fail, cause:{}", Throwables.getStackTraceAsString(e));
            return Response.fail("query.byId.fail");
        }
    }

    @Override
    public Response<List<DoctorPig>> queryByIds(@NotNull(message = "input.ids.empty") List<Long> ids) {
        try{
            return Response.ok(DoctorPigDao.findByIds(ids));
        }catch (Exception e){
            log.error("query by ids fail, cause:{}", Throwables.getStackTraceAsString(e));
            return Response.fail("query.byIds.fail");
        }
    }

    @Override
    public Response<Paging<DoctorPig>> pagingDoctorPig(Map<String, Object> criteria, Integer pageNo, Integer size) {
        try{
            if(isNull(criteria) || Iterables.isEmpty(criteria.entrySet())){
                return Response.fail("paging.DoctorPig.fail");
            }
            PageInfo pageInfo = new PageInfo(pageNo, size);
            return Response.ok(DoctorPigDao.paging(pageInfo.getOffset(),pageInfo.getLimit(), criteria));
        }catch (Exception e){
            log.error("paging by map fail, cause:{}", Throwables.getStackTraceAsString(e));
            return Response.fail("paging.byMap.fail");
        }
    }
}
