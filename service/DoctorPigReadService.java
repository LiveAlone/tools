
import io.terminus.common.model.Paging;
import io.terminus.common.model.Response;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Desc: 猪基础信息表 service
 * Mail: yaoqj@terminus.io
 * author: yaoqijun
 * Date: 2016-05-21
 */
public interface DoctorPigReadService {

    Response<DoctorPig> queryById(@NotNull(message = "input.id.empty") Long id);

    Response<List<DoctorPig>> queryByIds(@NotNull(message = "input.ids.empty") List<Long> ids);

    Response<Paging<DoctorPig>> pagingDoctorPig(Map<String, Object> criteria, Integer pageNo, Integer size)
}
