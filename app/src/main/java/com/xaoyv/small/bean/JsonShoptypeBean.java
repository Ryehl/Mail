package com.xaoyv.small.bean;

import java.util.List;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:商品类目二级列表</p>
 *
 * @author Xaoyv
 * date 2020/10/22 11:35
 */
public class JsonShoptypeBean {

    /**
     * result : [{"id":"1001002","name":"女装","secondCategoryVo":[{"id":"1001002001","name":"外套"},{"id":"1001002002","name":"裙装"},{"id":"1001002003","name":"打底毛衣"},{"id":"1001002004","name":"卫衣"},{"id":"1001002005","name":"裤装"}]},{"id":"1001003","name":"男鞋","secondCategoryVo":[{"id":"1001003001","name":"皮鞋"},{"id":"1001003002","name":"商务休闲鞋"},{"id":"1001003003","name":"板鞋"},{"id":"1001003004","name":"帆布鞋"},{"id":"1001003005","name":"运动鞋"}]},{"id":"1001004","name":"女鞋","secondCategoryVo":[{"id":"1001004001","name":"高跟鞋"},{"id":"1001004002","name":"帆布鞋"},{"id":"1001004003","name":"豆豆鞋"},{"id":"1001004004","name":"板鞋"},{"id":"1001004005","name":"凉鞋"}]},{"id":"1001007","name":"美妆护肤","secondCategoryVo":[{"id":"1001007002","name":"男士护肤"},{"id":"1001007004","name":"彩妆"},{"id":"1001007005","name":"美妆工具"}]},{"id":"1001008","name":"手机数码","secondCategoryVo":[{"id":"1001008001","name":"手机"},{"id":"1001008002","name":"手机配件"},{"id":"1001008003","name":"照相机"},{"id":"1001008004","name":"影音娱乐"},{"id":"1001008005","name":"智能设备"}]},{"id":"1001005","name":"箱包手袋","secondCategoryVo":[{"id":"1001005002","name":"拉杆箱"},{"id":"1001005003","name":"双肩包"},{"id":"1001005001","name":"电脑包"}]}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1001002
         * name : 女装
         * secondCategoryVo : [{"id":"1001002001","name":"外套"},{"id":"1001002002","name":"裙装"},{"id":"1001002003","name":"打底毛衣"},{"id":"1001002004","name":"卫衣"},{"id":"1001002005","name":"裤装"}]
         */

        private String id;
        private String name;
        private List<SecondCategoryVoBean> secondCategoryVo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SecondCategoryVoBean> getSecondCategoryVo() {
            return secondCategoryVo;
        }

        public void setSecondCategoryVo(List<SecondCategoryVoBean> secondCategoryVo) {
            this.secondCategoryVo = secondCategoryVo;
        }

        public static class SecondCategoryVoBean {
            /**
             * id : 1001002001
             * name : 外套
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
