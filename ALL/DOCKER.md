###docker基础
#### docker centos7 安装
#### LICSLAN:登陆你的Linux服务器
    sudo yum remove docker \
                      docker-client \
                      docker-client-latest \
                      docker-common \
                      docker-latest \
                      docker-latest-logrotate \
                      docker-logrotate \
                      docker-selinux \
                      docker-engine-selinux \
                      docker-engine
    安装一些系统工具
    sudo yum install -y yum-utils device-mapper-persistent-data lvm2
    添加软件源信息
    sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    更新yum缓存
    sudo yum makecache fast
    安装Docker-ce
    sudo yum -y install docker-ce
    启动 Docker 后台服务
    sudo systemctl start docker
    测试运行hello-world
    [root@licslan ~]# docker run hello-world
    
    使用脚本安装 Docker
    1、使用 sudo 或 root 权限登录 Centos。
    2、确保 yum 包更新到最新。
        $ sudo yum update
    3、执行 Docker 安装脚本。
        $ curl -fsSL https://get.docker.com -o get-docker.sh
        $ sudo sh get-docker.sh
        执行这个脚本会添加 docker.repo 源并安装 Docker。
    4、启动 Docker 进程。
        sudo systemctl start docker
    5、验证 docker 是否安装成功并在容器中执行一个测试的镜像。
        $ sudo docker run hello-world
        docker ps
    到此，Docker 在 CentOS 系统的安装完成。
    镜像加速
    鉴于国内网络问题，后续拉取 Docker 镜像十分缓慢，我们可以需要配置加速器来解决，我使用的是网易的镜像地址：http://hub-mirror.c.163.com。
    新版的 Docker 使用 /etc/docker/daemon.json（Linux） 或者 %programdata%\docker\config\daemon.json（Windows） 来配置 Daemon。
    请在该配置文件中加入（没有该文件的话，请先建一个）：
    {
      "registry-mirrors": ["http://hub-mirror.c.163.com"]
    }
    删除 Docker CE
    执行以下命令来删除 Docker CE：
    $ sudo yum remove docker-ce
    $ sudo rm -rf /var/lib/docker
    
    ok now  you can play with docker 
    docker is a good contaier to run your app !
                  