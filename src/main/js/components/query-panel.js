import React, { useState } from "react";

import {
    Button,
    List,
    Skeleton,
    Input,
    Col,
    Row,
    Typography,
    Result,
} from "antd";

import { Status, getFilterReposFetcher } from "../const";
import { appStyle, inputStyle, queryResponseStyle, queryStyle } from "./styles";

export const QueryPanel = () => {
    const [link, setLink] = useState("");
    const [token, setToken] = useState("");
    const [searchFile, setSearchFile] = useState("README.md");
    const [keyword, setKeyword] = useState("Hello");
    const [status, setStatus] = useState(Status.START);
    const [repos, setRepos] = useState([]);

    const handleChange = (method) => (e) => {
        method(() => e.currentTarget.value);
    };

    const handleClick = () => {
        setStatus(Status.LOADING);

        const data = {
            keyword,
            link,
            token,
            searchFile,
        };

        getFilterReposFetcher(data)
            .then((res) => res.json())
            .then((res) => {
                setStatus(Status.SUCCESS);
                setRepos(res);
            })
            .catch(() => {
                setStatus(Status.ERROR);
            });
    };

    return (
        <Row style={appStyle}>
            <Col span={1} />
            <Col span={7} style={queryStyle}>
                <Typography.Title level={3} style={{ marginTop: "16px" }}>
                    Search through yours repos
                </Typography.Title>
                <Input
                    placeholder="GitHub organization link"
                    value={link}
                    onChange={handleChange(setLink)}
                    style={inputStyle}
                />
                <Input
                    placeholder="GitHub access token"
                    value={token}
                    onChange={handleChange(setToken)}
                    style={inputStyle}
                />
                <Input
                    placeholder="Search file"
                    value={searchFile}
                    onChange={handleChange(setSearchFile)}
                    style={inputStyle}
                />
                <Input
                    placeholder="Keyword"
                    value={keyword}
                    onChange={handleChange(setKeyword)}
                    style={inputStyle}
                />
                <Button
                    type="primary"
                    loading={status === Status.LOADING}
                    onClick={handleClick}
                    style={{
                        ...inputStyle,
                        width: "100%",
                    }}
                >
                    Go
                </Button>
            </Col>
            <Col span={2} />
            <Col span={13} style={queryResponseStyle}>
                <Typography.Title level={3} style={{ marginTop: "16px" }}>
                    Get your filtered repos here
                </Typography.Title>
                {status === Status.LOADING && <Skeleton />}
                {status === Status.ERROR && (
                    <Result
                        status="warning"
                        title="There are some problems with searching repos"
                        extra={
                            <Button
                                type="primary"
                                key="console"
                                onClick={handleClick}
                            >
                                Retry
                            </Button>
                        }
                    />
                )}
                {status !== Status.LOADING && status !== Status.ERROR && (
                    <List
                        dataSource={repos}
                        renderItem={({ name, url }) => (
                            <List.Item
                                actions={[
                                    <a href={url} key={name}>
                                        Link
                                    </a>,
                                ]}
                            >
                                {name}
                            </List.Item>
                        )}
                    />
                )}
            </Col>
            <Col span={1} />
        </Row>
    );
};