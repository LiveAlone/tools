
import io.terminus.common.model.Paging;
import io.terminus.common.model.Response;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Desc: 猪Track 信息表 service
 * Mail: yaoqj@terminus.io
 * author: yaoqijun
 * Date: 2016-05-21
 */
public interface DoctorPigTrackReadService {

    Response<DoctorPigTrack> queryById(@NotNull(message = "input.id.empty") Long id);

    Response<List<DoctorPigTrack>> queryByIds(@NotNull(message = "input.ids.empty") List<Long> ids);

    Response<Paging<DoctorPigTrack>> pagingDoctorPigTrack(Map<String, Object> criteria, Integer pageNo, Integer size)
}
