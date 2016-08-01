
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
 * Desc: 猪Track 信息表 service
 * Mail: yaoqj@terminus.io
 * author: yaoqijun
 * Date: 2016-05-21
 */
@Slf4j
@Service
public class DoctorPigTrackReadServiceImpl implements DoctorPigTrackReadService{

    private final DoctorPigTrackDao DoctorPigTrackDao;

    public DoctorPigTrackReadServiceImpl(DoctorPigTrackDao DoctorPigTrackDao){
        this.DoctorPigTrackDao = DoctorPigTrackDao;
    }

    @Override
    public Response<DoctorPigTrack> queryById(@NotNull(message = "input.id.empty") Long id) {
        try{
            return Response.ok(DoctorPigTrackDao.findById(id));
        }catch (Exception e){
            log.error("query by id fail, cause:{}", Throwables.getStackTraceAsString(e));
            return Response.fail("query.byId.fail");
        }
    }

    @Override
    public Response<List<DoctorPigTrack>> queryByIds(@NotNull(message = "input.ids.empty") List<Long> ids) {
        try{
            return Response.ok(DoctorPigTrackDao.findByIds(ids));
        }catch (Exception e){
            log.error("query by ids fail, cause:{}", Throwables.getStackTraceAsString(e));
            return Response.fail("query.byIds.fail");
        }
    }

    @Override
    public Response<Paging<DoctorPigTrack>> pagingDoctorPigTrack(Map<String, Object> criteria, Integer pageNo, Integer size) {
        try{
            if(isNull(criteria) || Iterables.isEmpty(criteria.entrySet())){
                return Response.fail("paging.DoctorPigTrack.fail");
            }
            PageInfo pageInfo = new PageInfo(pageNo, size);
            return Response.ok(DoctorPigTrackDao.paging(pageInfo.getOffset(),pageInfo.getLimit(), criteria));
        }catch (Exception e){
            log.error("paging by map fail, cause:{}", Throwables.getStackTraceAsString(e));
            return Response.fail("paging.byMap.fail");
        }
    }
}
