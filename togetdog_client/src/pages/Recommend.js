import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { authAtom, userState } from "../recoil";
import axios from "axios";

import { BACKEND_URL } from "../config";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  RecommendWrapper,
  DropdownWrapper,
  FriendListWrapper,
  CheckBoxWrapper,
} from "../styles/RecommendEmotion";
import { DogImgWrapper } from "../styles/CreateAppointmentEmotion";
import OrangeCharacterBtn from "../components/OrangeCharacterBtn";
import YellowCharacterBtn from "../components/YellowCharacterBtn";
import Boy from "../assets/boy.png";
import Girl from "../assets/girl.png";
import { MainColorShortBtn } from "../styles/BtnsEmotion";

const SingleFriend = ({ item }) => {
  const navigate = useNavigate();

  return (
    <div className="singleDog">
      <DogImgWrapper>
        <div
          className="dogProfileCircle"
          onClick={() =>
            navigate(`/feed/${item.userId}`, { state: { dogId: item.dogId } })
          }>
          <img
            src={"https://togetdog.site/image/dog/" + item.dogProfile}
            alt="dog_img"
            className="dogProfileImg"
          />
        </div>
      </DogImgWrapper>
      <div
        className="dogInfo"
        onClick={() =>
          navigate(`/feed/${item.userId}`, { state: { dogId: item.dogId } })
        }>
        <div className="dogNameWrapper">
          <p className="dogName">{item.dogName}</p>
          <p className="ownerName">
            <FontAwesomeIcon icon="fa-user" /> {item.nickname}
          </p>
        </div>
        <div className="dogType">
          {item.dogType} /{" "}
          {item.dogAge < 12 ? item.dogAge : Math.floor(item.dogAge / 12)}
          {item.dogAge < 12 ? "개월" : "살"}
          <div className="genderWrapper">
            <img
              src={item.dogGender === "male" ? Boy : Girl}
              alt="gender"
              className="genderImg"
            />
          </div>
        </div>
        <div className="characters-box">
          <OrangeCharacterBtn
            text={`#${item.dogNeutered ? "중성화" : "중성화 X"}`}
          />
          <YellowCharacterBtn
            text={`#${item.dogCharacter1 === "obedient" ? "온순함" : "사나움"}`}
          />
          <YellowCharacterBtn
            text={`#${item.dogCharacter2 === "active" ? "활동적" : "비활동적"}`}
          />
        </div>
      </div>
    </div>
  );
};

const FriendsList = ({ friends }) => {
  const [checkedItems, setCheckedItems] = useState([
    false,
    false,
    false,
    false,
  ]);
  const [render, setRender] = useState(false);

  const issues = [...Array(4).keys()];
  const characters = ["온순함", "사나움", "활동적", "비활동적"];

  const Issue = ({ issue, idx }) => {
    const [bChecked, setChecked] = useState(checkedItems[idx]);
    const checkHandler = ({ target }) => {
      setChecked(!bChecked);
      checkedItemHandler(issue, target.checked);
    };

    return (
      <div>
        <input
          type="checkbox"
          checked={bChecked}
          onChange={(e) => checkHandler(e)}
        />
      </div>
    );
  };

  const checkedItemHandler = (id) => {
    let tempCheckedItems = checkedItems;
    tempCheckedItems[id] = !tempCheckedItems[id];
    setCheckedItems(tempCheckedItems);
    setRender(!render);
  };

  const renderFriends = () => {
    const tempFilter = [];

    if (friends && friends.length) {
      for (let i = 0; i < friends.length; i++) {
        const tempFriend = (
          <SingleFriend key={i} item={friends[i]}></SingleFriend>
        );
        let pushFlag = true;
        if (
          checkedItems[0] ||
          checkedItems[1] ||
          checkedItems[2] ||
          checkedItems[3]
        ) {
          if (checkedItems[0] && friends[i].dogCharacter1 === "obedient") {
            if (
              checkedItems[2] &&
              !checkedItems[3] &&
              friends[i].dogCharacter2 === "inactive"
            ) {
              pushFlag = false;
            } else if (
              !checkedItems[2] &&
              checkedItems[3] &&
              friends[i].dogCharacter2 === "active"
            ) {
              pushFlag = false;
            }
          } else if (
            checkedItems[1] &&
            friends[i].dogCharacter1 === "disobedient"
          ) {
            if (
              checkedItems[2] &&
              !checkedItems[3] &&
              friends[i].dogCharacter2 === "inactive"
            ) {
              pushFlag = false;
            } else if (
              !checkedItems[2] &&
              checkedItems[3] &&
              friends[i].dogCharacter2 === "active"
            ) {
              pushFlag = false;
            }
          } else if (checkedItems[2] && friends[i].dogCharacter2 === "active") {
            if (
              checkedItems[0] &&
              !checkedItems[1] &&
              friends[i].dogCharacter1 === "disobedient"
            ) {
              pushFlag = false;
            } else if (
              !checkedItems[0] &&
              checkedItems[1] &&
              friends[i].dogCharacter1 === "obedient"
            ) {
              pushFlag = false;
            }
          } else if (
            checkedItems[3] &&
            friends[i].dogCharacter2 === "inactive"
          ) {
            if (
              checkedItems[0] &&
              !checkedItems[1] &&
              friends[i].dogCharacter1 === "disobedient"
            ) {
              pushFlag = false;
            } else if (
              !checkedItems[0] &&
              checkedItems[1] &&
              friends[i].dogCharacter1 === "obedient"
            ) {
              pushFlag = false;
            }
          } else {
            pushFlag = false;
          }
        }
        if (pushFlag) {
          tempFilter.push(tempFriend);
        }
      }
      if (!tempFilter.length) {
        tempFilter.push(
          <p className="noFriends" key={0}>
            동네에 추천해줄 산책 친구가 없습니다.
          </p>
        );
      }
    } else {
      tempFilter.push(
        <p className="noFriends" key={0}>
          동네에 추천해줄 산책 친구가 없습니다.
        </p>
      );
    }
    return tempFilter;
  };

  return (
    <FriendListWrapper>
      <CheckBoxWrapper>
        {issues.map((issue, idx) => (
          <div className="checkBox" key={idx}>
            <div>{characters[idx]}</div>
            <Issue issue={issue} idx={idx}></Issue>
          </div>
        ))}
      </CheckBoxWrapper>
      {renderFriends()}
    </FriendListWrapper>
  );
};

const Recommend = () => {
  const [user, setUser] = useRecoilState(userState);
  const [userData, setUserData] = useState();
  const [view, setView] = useState(false);
  const [currentDog, setCurrentDog] = useState(-1);
  const [currentDogName, setCurrentDogName] = useState();
  const [friends, setFriends] = useState();
  const auth = useRecoilValue(authAtom);
  const setAuth = useSetRecoilState(authAtom);

  const handleLogout = () => {
    setUser(null);
    localStorage.removeItem("user");
    setAuth(null);
    console.log("로그아웃이 정상적으로 처리되었습니다.");
    navigate("/login");
  };

  useEffect(() => {
    if (!auth || !localStorage.getItem("recoil-persist")) {
      navigate("/login");
      return;
    }

    axios
      .get(`${BACKEND_URL}/user/includesDog/${user.userId}`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: auth,
        },
      })
      .then((response) => {
        setUserData(response.data);
        if (response.data.user.dogs[0]) {
          setCurrentDog(response.data.user.dogs[0].dogId);
          setCurrentDogName(response.data.user.dogs[0].dogName);
        } else {
          setCurrentDogName("---");
        }
      })
      .catch((error) => {
        // 오류발생시 실행
        if (error.response.status === 404) {
          navigate("/*");
        } else if (error.response.status === 401) {
          alert("자동 로그아웃되었습니다.");
          handleLogout();
        }
      });
  }, []);

  useEffect(() => {
    if (currentDog !== -1) {
      axios
        .get(`${BACKEND_URL}/meeting/${currentDog}`, {
          headers: {
            "Content-Type": "application/json",
            Authorization: auth,
          },
        })
        .then((response) => {
          setFriends(response.data.dogs);
        })
        .catch((error) => {
          // 오류발생시 실행
        });
    }
  }, [currentDog]);

  const onClick = (item) => {
    setCurrentDog(item.dogId);
    setCurrentDogName(item.dogName);
  };

  const Dropdown = () => {
    return (
      <div className="dropdownListWrapper">
        {userData.user.dogs.map((item, idx) => (
          <div className="dropdownList" onClick={() => onClick(item)} key={idx}>
            <li className="dropdownText ">{item.dogName}</li>
          </div>
        ))}
      </div>
    );
  };

  const navigate = useNavigate();

  return (
    <RecommendWrapper>
      {currentDog === -1 ? (
        <div className="no-dog-recommend">
          <p className="no-dog-txt">
            강아지를 등록하신 후<br />
            산책 친구를 추천받으실 수 있습니다.
          </p>
          <MainColorShortBtn
            onClick={() => navigate("/dogregister", { state: { dogs: [] } })}>
            강아지 등록
          </MainColorShortBtn>
        </div>
      ) : (
        <DropdownWrapper>
          <div className="flex fixed">
            <ul onClick={() => setView(!view)} className="dropdown">
              <div className="dropdownListHeader">
                <span className="dropdownText">{currentDogName}</span>
                <div className="iconWrapper">
                  <FontAwesomeIcon
                    icon={view ? "fa-caret-up" : "fa-caret-down"}
                  />
                </div>
              </div>
              {view && <Dropdown />}
            </ul>
            <div className="plainText">의 산책 친구들</div>
          </div>
        </DropdownWrapper>
      )}
      {friends !== [] ? <FriendsList friends={friends}></FriendsList> : null}
    </RecommendWrapper>
  );
};

export default Recommend;
