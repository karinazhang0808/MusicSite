DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert  into `t_user`(`id`,`username`,`password`,`role`) values (1,'admin','admin','1');
DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `descp` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert  into `t_role`(`id`,`name`) values (1,'管理员');

DROP TABLE IF EXISTS `t_member`;

CREATE TABLE `t_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `tel` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_channel`;

CREATE TABLE `t_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `descp` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_music`;

CREATE TABLE `t_music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `descp` varchar(200) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `settime` datetime DEFAULT NULL,
  `words` varchar(200) DEFAULT NULL,
  `bofang` varchar(200) DEFAULT NULL,
  `xihuan` varchar(200) DEFAULT NULL,
  `channel` int(11) DEFAULT NULL,
  `musicpath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_memberplaylist`;

CREATE TABLE `t_memberplaylist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member` int(11) DEFAULT NULL,
  `music` int(11) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `playcount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_memberloves`;

CREATE TABLE `t_memberloves` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member` int(11) DEFAULT NULL,
  `music` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

